package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.base.SysException;
import com.denghb.simplex.consts.SysResourceConsts;
import com.denghb.simplex.consts.SysUserConsts;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.domain.SysUser;
import com.denghb.simplex.sys.domain.SysUserToken;
import com.denghb.simplex.sys.model.req.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

@Service
public class AuthAccessServiceImpl implements AuthAccessService {

    private final static Pattern IGNORE_EXT = Pattern.compile(".*(.html|.png|.svg|.txt|.js|.css|.ico|.ttf|.eot|.woff|.map)$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private Eorm db;

    @Override
    public int addLog(SysAccessLogReq req) {
        SysAccessLog log = new SysAccessLog();
        BeanUtils.copyProperties(req, log);
        log.setStartTime(new Date());
        db.insert(log);
        return log.getId();
    }

    @Override
    public void setEndTime(int id) {
        SysAccessLog log = new SysAccessLog();
        log.setId(id);
        log.setEndTime(new Date());
        db.update(log);
    }

    @Override
    public boolean isOpened(String method, String uri) {
        if (IGNORE_EXT.matcher(uri).matches()) {
            return true;
        }

        String sql = "select opened from tb_sys_resource where path = ? and type = ? and deleted = 0 ";
        Integer opened = db.selectOne(Integer.class, sql, method + uri, SysResourceConsts.Type.API);
        return (null != opened && 1 == opened);
    }

    @Override
    public Credential validate(RequestInfo requestInfo) throws SysException {
        String accessToken = requestInfo.getAccessToken();
        if (null == accessToken) {
            throw new SysException(403, "非法访问");
        }
        SysUserToken sysUserToken = db.selectOne(SysUserToken.class, "select * from tb_sys_user_token where access_token = ? and deleted = 0", accessToken);
        if (null == sysUserToken) {
            throw new SysException(403, "非法访问");
        }

        if (!requestInfo.getIp().equals(sysUserToken.getIp())) {
            throw new SysException(401, "登录的IP变了，请重新登录");
        }
        String path = requestInfo.getMethod() + requestInfo.getUri();

        SysUser sysUser = db.selectOne(SysUser.class, "select * from tb_sys_user where id = ? and deleted = 0", sysUserToken.getSysUserId());
        if (null == sysUser || SysUserConsts.Status.NORMAL != sysUser.getStatus()) {
            throw new SysException(401, "账户状态有误，请重新登录");
        }
        // 校验uri权限

        String sql = "select distinct sr.path from tb_sys_user su left join tb_sys_role_resource srr on su.sys_role_id = srr.sys_role_id left join tb_sys_resource sr on sr.id = srr.sys_resource_id where su.id = ? and sr.type = ? and su.deleted = 0 and sr.opened = 0 and sr.deleted = 0 and srr.deleted = 0 and sr.path = ? ";
        String s = db.selectOne(String.class, sql, sysUser.getId(), SysResourceConsts.Type.API, path);
        if (null == s) {
            throw new SysException(403, "账户无访问权限");
        }

        Credential credential = new Credential();
        credential.setId(sysUser.getId());
        credential.setUsername(sysUser.getUsername());
        credential.setName(sysUser.getName());
        return credential;
    }


}
