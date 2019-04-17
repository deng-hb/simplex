package com.denghb.simplex.sys.service.impl;


import com.denghb.eorm.domain.Paging;
import com.denghb.eorm.domain.PagingResult;
import com.denghb.simplex.base.BizException;
import com.denghb.simplex.consts.SysResourceConsts;
import com.denghb.simplex.consts.SysUserConsts;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.domain.SysUser;
import com.denghb.simplex.sys.domain.SysUserPwd;
import com.denghb.simplex.sys.domain.SysUserToken;
import com.denghb.simplex.sys.model.req.SysUserReq;
import com.denghb.simplex.sys.model.res.SysMenuRes;
import com.denghb.simplex.sys.model.res.SysUserRes;
import com.denghb.simplex.sys.model.res.SysUserSignInRes;
import com.denghb.simplex.sys.service.BaseService;
import com.denghb.simplex.sys.service.SysResourceService;
import com.denghb.simplex.sys.service.SysUserService;
import com.denghb.simplex.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public void save(SysUserReq req) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);

        if (null == req.getId()) {
            Integer id = db.selectOne(Integer.class, "select id from tb_sys_user where username = ? ", sysUser.getUsername());
            if (null != id) {
                throw new BizException("用户名已存在");
            }
            // 创建账户
            db.insert(sysUser);

            // FIXME 初始化密码
            String password = RandomStringUtils.randomAlphanumeric(8);
            log.info("init password:{}", password);

            password = Md5Utils.md5(password);
            password = Md5Utils.md5(password);
            String salt = RandomStringUtils.randomAlphanumeric(10);

            SysUserPwd sysUserPwd = new SysUserPwd();
            sysUserPwd.setSysUserId(sysUser.getId());
            sysUserPwd.setPwd(Md5Utils.md5(password + salt));
            sysUserPwd.setSalt(salt);
            db.insert(sysUserPwd);
        } else {
            sysUser.setUsername(null);
            db.update(sysUser);
        }
    }

    @Transactional
    @Override
    public void del(IdReq req) {
        Credential credential = CredentialContextHolder.get();
        int res = db.execute("update tb_sys_user set operator = ?, deleted = 1 where id = ? and deleted = 0 ", credential.getId(), req.getId());
        if (1 != res) {
            throw new BizException("删除失败");
        }
        db.execute("update tb_sys_user_pwd set deleted = 1 where sys_user_id = ? and deleted = 0 ", req.getId());
    }

    @Override
    public PageRes<SysUserRes> list(PageReq req) {
        Paging paging = new Paging() {
            @Override
            public String[] getSorts() {
                return new String[]{};
            }
        };
        paging.setPage(req.getPage());
        paging.setPageSize(req.getPageSize());

        String sql = "select su.*,su2.name operatorName from tb_sys_user su left join tb_sys_user su2 on su2.id = su.operator where su.deleted = 0 ";
        PagingResult<SysUserRes> result = db.page(SysUserRes.class, new StringBuffer(sql), paging);

        return new PageRes<SysUserRes>(result.getList(), result.getPaging().getTotal());
    }

    // 不需要加事务
    @Override
    public SysUserSignInRes signIn(String username, String password, String ip, String userAgent) {
        // 查询用户名
        SysUser sysUser = db.selectOne(SysUser.class, "select * from tb_sys_user where username = ? ", username);
        if (null == sysUser) {
            throw new BizException("用户名不存在");// 可以模糊提示
        }
        if (SysUserConsts.Status.LOCK_SIGN_ERR == sysUser.getStatus()) {
            throw new BizException("密码输入错误过多，已锁住");
        }
        // 验证密码
        String validPwdSql = "select * from tb_sys_user_pwd where sys_user_id = ? and deleted = 0";
        SysUserPwd sysUserPwd = db.selectOne(SysUserPwd.class, validPwdSql, sysUser.getId());
        if (null == sysUserPwd || !sysUserPwd.getPwd().equals(Md5Utils.md5(password + sysUserPwd.getSalt()))) {

            sysUser.setSignErrorSize(sysUser.getSignErrorSize() + 1);
            sysUser.setUpdatedTime(null);

            int diff = sysUser.getSignErrorLimit() - sysUser.getSignErrorSize();
            if (0 >= diff) {
                sysUser.setStatus(SysUserConsts.Status.LOCK_SIGN_ERR);
            }
            db.update(sysUser);
            if (0 >= diff) {
                throw new BizException("密码输入错误过多，已锁住");
            } else {
                throw new BizException("密码输入错误，还有" + diff + "次机会");
            }
        }
        // 将之前的token都删除
        db.execute("update tb_sys_user_token set deleted = 1 where deleted = 0 and sys_user_id = ?", sysUser.getId());
        // 生成token
        String accessToken = UUID.randomUUID().toString().replaceAll("-", "");


        SysUserToken sysUserToken = new SysUserToken();
        sysUserToken.setAccessToken(accessToken);
        sysUserToken.setSysUserId(sysUser.getId());

        sysUserToken.setUserAgent(userAgent);
        sysUserToken.setIp(ip);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        sysUserToken.setExpireTime(calendar.getTime());
        db.insert(sysUserToken);

        // 错误次数重置
        sysUser.setSignErrorSize(0);
        sysUser.setUpdatedTime(null);
        db.update(sysUser);

        SysUserSignInRes res = new SysUserSignInRes();
        res.setToken(accessToken);
        return res;
    }

    @Override
    public void unlockSignError(IdReq req) {
        Credential credential = CredentialContextHolder.get();
        String sql = "update tb_sys_user set sign_error_size = 0, status = ? where id = ? ";
        int res = db.execute(sql, SysUserConsts.Status.NORMAL, req.getId(), credential.getId());
        if (1 != res) {
            throw new BizException("操作失败");
        }
    }

    @Override
    public List<SysMenuRes> menu(int sysUserId) {
        // 查询当前用户的菜单
        String sql = "select distinct sr.* from tb_sys_user su left join tb_sys_role_resource srr on su.sys_role_id = srr.sys_role_id " +
                "left join tb_sys_resource sr on sr.id = srr.sys_resource_id " +
                "where su.id = ? and sr.type = ? and su.deleted = 0 and sr.deleted = 0 and srr.deleted = 0 order by parent_id, seq asc";
        return db.select(SysMenuRes.class, sql, sysUserId, SysResourceConsts.Type.MENU);
    }
}
