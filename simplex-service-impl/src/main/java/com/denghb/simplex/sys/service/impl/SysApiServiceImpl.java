package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.sys.domain.SysApi;
import com.denghb.simplex.sys.model.SysApiReq;
import com.denghb.simplex.sys.model.SysApiRes;
import com.denghb.simplex.sys.service.BaseService;
import com.denghb.simplex.sys.service.SysApiService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/15 22:14
 */
@Service
public class SysApiServiceImpl extends BaseService implements SysApiService {

    @Override
    public void save(SysApiReq req) {
        Credential credential = CredentialContextHolder.get();
        SysApi sysApi = new SysApi();
        BeanUtils.copyProperties(req, sysApi);

        sysApi.setOperator(credential.getId());

        if (null == sysApi.getId()) {
            db.insert(sysApi);
        } else {
            db.update(sysApi);
        }
    }

    @Override
    public List<SysApiRes> list() {
        return db.select(SysApiRes.class, "select * from tb_sys_api where deleted = 0");
    }
}
