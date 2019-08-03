package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.base.SysException;
import com.denghb.simplex.consts.SysResourceConsts;
import com.denghb.simplex.consts.SysUserConsts;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.service.BaseService;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.domain.SysUser;
import com.denghb.simplex.sys.domain.SysUserToken;
import com.denghb.simplex.sys.model.req.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

@Service
public class AuthAccessServiceImpl extends BaseService implements AuthAccessService {

    private final static Pattern IGNORE_EXT = Pattern.compile(".*(.html|.png|.svg|.txt|.js|.css|.ico|.ttf|.eot|.woff|.woff2|.map)$", Pattern.CASE_INSENSITIVE);

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

        String sql = ""/*{
            select opened from tb_sys_resource where uri = ? and method = ? and type = ? and deleted = 0
        }*/;
        Integer opened = db.selectOne(Integer.class, sql, uri, method, SysResourceConsts.Type.API);
        return (null != opened && 1 == opened);
    }

    @Override
    public Credential validate(RequestInfo requestInfo) throws SysException {
        String method = requestInfo.getMethod(), uri = requestInfo.getUri();
        if (isOpened(method, uri)) {
            return null;
        }

        String accessToken = requestInfo.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            throw new SysException(403, "非法访问");
        }

        String sql1 = ""/*{
            select * from tb_sys_user_token where access_token = ? and expire_time > now() and deleted = 0
        }*/;
        SysUserToken sysUserToken = db.selectOne(SysUserToken.class, sql1, accessToken);
        if (null == sysUserToken) {
            throw new SysException(403, "登录失效，请重新登录");
        }

        if (!requestInfo.getIp().equals(sysUserToken.getIp())) {
            throw new SysException(401, "登录的IP变了，请重新登录");
        }

        int sysUserId = sysUserToken.getSysUserId();

        String sql2 = ""/*{
            select * from tb_sys_user where id = ? and deleted = 0
        }*/;
        SysUser sysUser = db.selectOne(SysUser.class, sql2, sysUserId);
        if (null == sysUser || SysUserConsts.Status.NORMAL != sysUser.getStatus()) {
            throw new SysException(401, "账户状态有误，请重新登录");
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
            throw new SysException(403, "账户无访问权限：" + uri);
        }

        Credential credential = new Credential();
        credential.setId(sysUserId);
        credential.setUsername(sysUser.getUsername());
        credential.setName(sysUser.getName());
        return credential;
    }


}
