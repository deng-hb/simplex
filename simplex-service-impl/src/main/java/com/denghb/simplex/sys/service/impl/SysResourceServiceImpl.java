package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.base.BizException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.sys.domain.SysResource;
import com.denghb.simplex.sys.model.req.SysResourceReq;
import com.denghb.simplex.sys.model.res.SysResourceRes;
import com.denghb.simplex.sys.service.BaseService;
import com.denghb.simplex.sys.service.SysResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:55
 */
@Service
public class SysResourceServiceImpl extends BaseService implements SysResourceService {

    @Override
    public void save(SysResourceReq req) {
        Credential credential = CredentialContextHolder.get();
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(req, sysResource);

        sysResource.setOperator(credential.getId());
        if (null == req.getId()) {
            db.insert(sysResource);
        } else {
            db.update(sysResource);
        }
    }

    @Override
    public void del(int id) {
        Credential credential = CredentialContextHolder.get();
        int res = db.execute("update tb_sys_resource set operator = ?, deleted = 1 where id = ? and deleted = 0 ", credential.getId(), id);
        if (1 != res) {
            throw new BizException("删除失败");
        }
    }

    @Override
    public List<SysResourceRes> list() {
        return db.select(SysResourceRes.class, "select * from tb_sys_resource where deleted = 0 order by parent_id, seq asc");
    }

}
