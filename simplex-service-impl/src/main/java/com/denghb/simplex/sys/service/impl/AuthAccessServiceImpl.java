package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.domain.SysUser;
import com.denghb.simplex.sys.model.SysAccessLogReq;
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

        String sql = "select opened from tb_sys_api where method = ? and uri = ? and deleted = 0 ";
        Integer opened = db.selectOne(Integer.class, sql, method, uri);
        return (null != opened && 1 == opened);
    }

    @Override
    public Credential validate(RequestInfo requestInfo) {
        String accessToken = requestInfo.getAccessToken();
        if (null == accessToken) {
            return null;
        }
        String sql = "select su.* from tb_sys_user su left join tb_sys_user_token sut on su.id = sut.sys_user_id where sut.access_token = ? and sut.ip = ? ";
        SysUser sysUser = db.selectOne(SysUser.class, sql, accessToken, requestInfo.getIp());
        if (null == sysUser) {
            return null;
        }
        // TODO 校验uri权限

        Credential credential = new Credential();
        credential.setId(sysUser.getId());
        credential.setUsername(sysUser.getUsername());
        credential.setName(sysUser.getName());
        return credential;
    }
}
