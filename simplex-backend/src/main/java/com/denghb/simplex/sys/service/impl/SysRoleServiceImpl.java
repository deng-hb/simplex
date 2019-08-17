package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.service.impl.EserviceImpl;
import com.denghb.simplex.sys.domain.SysRole;
import com.denghb.simplex.sys.domain.SysRoleResource;
import com.denghb.simplex.sys.model.res.SysRoleInfoRes;
import com.denghb.simplex.sys.model.res.SysRoleRes;
import com.denghb.simplex.sys.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author denghb
 * @since 2019/4/17 21:21
 */
@Service
public class SysRoleServiceImpl extends EserviceImpl<SysRole> implements SysRoleService {

    @Override
    public PageRes<SysRoleRes> list(PageReq req) {

        String sql = ""/*{
            select sr.*,su.name operatorName from tb_sys_role sr
            left join tb_sys_user su on su.id = sr.operator where sr.deleted = 0
        }*/;
        return selectPage(SysRoleRes.class, sql, req);
    }

    @Transactional
    @Override
    public void setSysResourceIds(int sysRoleId, List<Integer> sysResourceIds) {
        Credential credential = CredentialContextHolder.get();
        int sysUserId = credential.getId();

        String sql = ""/*{
            select * from tb_sys_role_resource where sys_role_id = ? and deleted = 0
        }*/;
        List<SysRoleResource> old = db.select(SysRoleResource.class, sql, sysRoleId);

        List<Integer> addIds = new ArrayList<>();
        List<Integer> oldIds = new ArrayList<>();

        // 找出待删除的
        if (null != old && !old.isEmpty()) {
            List<Integer> delIds = new ArrayList<>();
            for (SysRoleResource srr : old) {
                Integer srId = srr.getSysResourceId();
                oldIds.add(srId);
                if (!sysResourceIds.contains(srId)) {
                    delIds.add(srr.getId());
                }
            }

            String delSQL = ""/*{
                update tb_sys_role_resource set deleted = 1, operator = ? where deleted = 0 and id = ?
            }*/;
            for (Integer id : delIds) {
                db.execute(delSQL, sysUserId, id);
            }
        }

        // 找出新增的
        if (!oldIds.isEmpty()) {
            for (Integer id : sysResourceIds) {
                if (!oldIds.contains(id)) {
                    addIds.add(id);
                }
            }
        } else {
            addIds.addAll(sysResourceIds);
        }

        SysRoleResource ssr = new SysRoleResource();
        ssr.setOperator(sysUserId);
        ssr.setSysRoleId(sysRoleId);
        for (Integer sysResourceId : addIds) {
            ssr.setId(null);
            ssr.setSysResourceId(sysResourceId);
            db.insert(ssr);
        }

    }

    @Override
    public List<Integer> listSysResourceId(int sysRoleId) {
        String sql = ""/*{
            select sys_resource_id from tb_sys_role_resource where sys_role_id = ? and deleted = 0
        }*/;
        return db.select(Integer.class, sql, sysRoleId);
    }

    @Override
    public List<SysRoleInfoRes> list() {
        String sql = ""/*{
            select id,name from tb_sys_role where deleted = 0
        }*/;
        return db.select(SysRoleInfoRes.class, sql);
    }
}
