package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.common.base.BizException;
import com.denghb.simplex.common.holder.Credential;
import com.denghb.simplex.common.holder.CredentialContextHolder;
import com.denghb.simplex.common.model.PageReq;
import com.denghb.simplex.common.model.PageRes;
import com.denghb.simplex.common.service.impl.EPageServiceImpl;
import com.denghb.simplex.sys.domain.SysRole;
import com.denghb.simplex.sys.domain.SysRoleResource;
import com.denghb.simplex.sys.model.req.SysRoleReq;
import com.denghb.simplex.sys.model.res.SysRoleInfoRes;
import com.denghb.simplex.sys.model.res.SysRoleRes;
import com.denghb.simplex.sys.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author denghb
 * @since 2019/4/17 21:21
 */
@Service
public class SysRoleServiceImpl extends EPageServiceImpl implements SysRoleService {

    @Override
    public PageRes<SysRoleRes> list(PageReq req) {

        String sql = ""/*{
            select sr.*,su.name operatorName from tb_sys_role sr
            left join tb_sys_user su on su.id = sr.operator where sr.deleted = 0
        }*/;
        PageRes<SysRoleRes> res = selectPage(SysRoleRes.class, sql, req);
        List<SysRoleRes> list = res.getList();
        if (null != list && !list.isEmpty()) {
            for (SysRoleRes r : list) {
                r.setSysResourceIds(getSysResourceIds(r.getId()));
            }
        }
        return res;
    }

    private List<Integer> getSysResourceIds(int sysRoleId) {

        String sql = ""/*{
            select sys_resource_id from tb_sys_role_resource where sys_role_id = ? and deleted = 0
        }*/;
        return db.select(Integer.class, sql, sysRoleId);
    }

    @Transactional
    @Override
    public void save(final SysRoleReq req) {
        Integer sysRoleId = req.getId();
        String existSQL = ""/*{
            select id from tb_sys_role where name = :name and deleted = 0
            #if (null != #id)
                and id != :id
            #end
        }*/;
        Integer existId = db.selectOne(Integer.class, existSQL, new HashMap<String, Object>() {{
            put("id", req.getId());
            put("name", req.getName());
        }});
        if (null != existId) {
            throw new BizException("名称已存在");
        }
        Credential credential = CredentialContextHolder.get();
        int sysUserId = credential.getId();

        List<Integer> sysResourceIds = req.getSysResourceIds();
        if (null == sysResourceIds) sysResourceIds = new ArrayList<Integer>();

        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(req, sysRole);
        sysRole.setOperator(sysUserId);
        if (null == req.getId()) {
            db.insert(sysRole);
            sysRoleId = sysRole.getId();
        } else {
            db.update(sysRole);

            String delSQL = ""/*{
                update tb_sys_role_resource set deleted = 1, operator = ?
                where sys_role_id = ? and deleted = 0 and sys_resource_id = ?
            }*/;

            List<Integer> oldSysResourceIds = getSysResourceIds(sysRoleId);
            if (null != oldSysResourceIds && !oldSysResourceIds.isEmpty()) {
                for (Integer sysResourceId : oldSysResourceIds) {
                    if (!sysResourceIds.contains(sysResourceId)) {
                        db.execute(delSQL, sysUserId, sysRoleId, sysResourceId);
                    } else {
                        sysResourceIds.remove(sysResourceId);
                    }
                }
            }
        }

        if (sysResourceIds.isEmpty()) {
            return;
        }
        // 新增
        SysRoleResource ssr = new SysRoleResource();
        ssr.setOperator(sysUserId);
        ssr.setSysRoleId(sysRoleId);
        for (Integer sysResourceId : sysResourceIds) {
            ssr.setId(null);
            ssr.setSysResourceId(sysResourceId);
            db.insert(ssr);
        }
    }

    @Override
    public List<SysRoleInfoRes> list() {
        String sql = ""/*{
            select id, name from tb_sys_role where deleted = 0
        }*/;
        return db.select(SysRoleInfoRes.class, sql);
    }

    @Override
    public void del(int id) {

        Credential credential = CredentialContextHolder.get();
        int userId = credential.getId();
        List<Integer> oldResourceIds = getSysResourceIds(id);
        if (null != oldResourceIds && !oldResourceIds.isEmpty()) {
            throw new BizException("删除失败，当前角色还有资源");
        }

        String sql = ""/*{
            update tb_sys_role set operator = ?, deleted = 1 where id = ? and deleted = 0
        }*/;
        int res = db.execute(sql, userId, id);
        Assert.isTrue(1 == res, "删除失败");

    }
}
