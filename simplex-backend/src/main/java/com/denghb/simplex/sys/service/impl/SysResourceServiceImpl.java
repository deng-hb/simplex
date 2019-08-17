package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.base.BizException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.service.Eservice;
import com.denghb.simplex.sys.domain.SysResource;
import com.denghb.simplex.sys.model.req.SysResourceQueryReq;
import com.denghb.simplex.sys.model.req.SysResourceReq;
import com.denghb.simplex.sys.model.res.SysResourceRes;
import com.denghb.simplex.sys.service.SysResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author denghb
 * @since 2019/4/13 21:55
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private Eorm db;

    @Override
    public void save(SysResourceReq req) {
        Credential credential = CredentialContextHolder.get();
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(req, sysResource);

        sysResource.setOperator(credential.getId());
        if (null == req.getId()) {
            db.insert(sysResource);
        } else {
            if (null != req.getParentId() && req.getParentId().intValue() == req.getId()) {
                throw new BizException("父节点不能是自己");
            }
            db.update(sysResource);
        }
    }

    @Override
    public void del(int id) {
        Credential credential = CredentialContextHolder.get();
        String sql = ""/*{
            update tb_sys_resource set operator = ?, deleted = 1 where id = ? and deleted = 0
        }*/;
        int res = db.execute(sql, credential.getId(), id);
        Assert.isTrue(1 == res, "删除失败");
    }

    @Override
    public List<SysResourceRes> list(SysResourceQueryReq req) {
        String sql = ""/*{
            select * from tb_sys_resource where deleted = 0
            #if (null != #type && '' != #type)
                and type = :type
            #end
            #if (null != #title && '' != #title)
                and title like concat('%', :title, '%')
            #end
            #if (null != #uri && '' != #uri)
                and uri like concat('%', :uri, '%')
            #end
            #if (null != #opened)
                and opened = :opened
            #end
            order by parent_id, seq asc
        }*/;
        return db.select(SysResourceRes.class, sql, req);
    }

}
