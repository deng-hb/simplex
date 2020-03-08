package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.common.base.AuthException;
import com.denghb.simplex.common.consts.SysResourceConsts;
import com.denghb.simplex.common.consts.SysUserConsts;
import com.denghb.simplex.common.holder.Credential;
import com.denghb.simplex.common.holder.RequestInfo;
import com.denghb.simplex.common.holder.RequestInfoContextHolder;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.domain.SysUser;
import com.denghb.simplex.sys.domain.SysUserToken;
import com.denghb.simplex.sys.model.req.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.regex.Pattern;

@Slf4j
@Service
public class AuthAccessServiceImpl implements AuthAccessService {

    private final static Pattern IGNORE_EXT = Pattern.compile(".*(.html|.png|.svg|.txt|.js|.css|.ico|.ttf|.eot|.woff|.woff2|.map)$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private Eorm db;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int addLog(SysAccessLogReq req) {
        SysAccessLog log = new SysAccessLog();
        BeanUtils.copyProperties(req, log);
        log.setStartTime(new Date());
        db.insert(log);
        return log.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

        String sql = ""/*{
            select opened from tb_sys_resource where uri = ? and method = ? and type = ? and deleted = 0
        }*/;
        Integer opened = db.selectOne(Integer.class, sql, uri, method, SysResourceConsts.Type.API);
        return (null != opened && 1 == opened);
    }

    @Override
    public Credential validate() throws AuthException {
        RequestInfo requestInfo = RequestInfoContextHolder.get();
        String method = requestInfo.getMethod(), uri = requestInfo.getUri();
        if (isOpened(method, uri)) {
            return null;
        }

        String accessToken = requestInfo.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            throw new AuthException("非法访问");
        }

        String sql1 = ""/*{
            select * from tb_sys_user_token where access_token = ? and expire_time > now() and deleted = 0
        }*/;
        SysUserToken sysUserToken = db.selectOne(SysUserToken.class, sql1, accessToken);
        if (null == sysUserToken) {
            throw new AuthException("登录失效，请重新登录");
        }

        if (!requestInfo.getIp().equals(sysUserToken.getIp())) {
            throw new AuthException("登录的IP变了，请重新登录");
        }

        int sysUserId = sysUserToken.getSysUserId();

        String sql2 = ""/*{
            select * from tb_sys_user where id = ? and deleted = 0
        }*/;
        SysUser sysUser = db.selectOne(SysUser.class, sql2, sysUserId);
        if (null == sysUser || SysUserConsts.Status.NORMAL != sysUser.getStatus()) {
            throw new AuthException("账户状态有误，请重新登录");
        }
        // 校验uri权限

        String sql = ""/*{
            select distinct sr.uri from tb_sys_user su
            left join tb_sys_role_resource srr on su.sys_role_id = srr.sys_role_id
            left join tb_sys_resource sr on sr.id = srr.sys_resource_id
            where su.id = ? and sr.type = ? and su.deleted = 0 and sr.opened = 0
            and sr.deleted = 0 and srr.deleted = 0 and sr.uri = ? and sr.method = ?
        }*/;
        String result = db.selectOne(String.class, sql, sysUserId, SysResourceConsts.Type.API, uri, method);
        if (null == result) {
            log.error("账户无访问权限：{}", uri);
            throw new AuthException("账户无访问权限：" + uri);
        }

        Credential credential = new Credential();
        credential.setId(sysUserId);
        credential.setUsername(sysUser.getUsername());
        credential.setName(sysUser.getName());
        return credential;
    }


}
