package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.base.Credential;
import com.denghb.simplex.sys.domain.SysAccessLog;
import com.denghb.simplex.sys.model.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthAccessServiceImpl implements AuthAccessService {

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
        String sql = "select opened from tb_sys_api where method = ? and uri = ? and deleted = 0 ";
        Integer opened = db.selectOne(Integer.class, sql, method, uri);
        return (null != opened && 1 == opened);
    }

    @Override
    public Credential validate(String accessToken, String ip, String userAgent) {
        return null;
    }
}
