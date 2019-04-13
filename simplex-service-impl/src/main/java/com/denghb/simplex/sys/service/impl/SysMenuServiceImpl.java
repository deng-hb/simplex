package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.base.BizException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.domain.SysMenu;
import com.denghb.simplex.sys.model.SysMenuReq;
import com.denghb.simplex.sys.model.SysMenuRes;
import com.denghb.simplex.sys.service.BaseService;
import com.denghb.simplex.sys.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:55
 */
@Service
public class SysMenuServiceImpl extends BaseService implements SysMenuService {

    @Override
    public void save(SysMenuReq req) {
        Credential credential = CredentialContextHolder.get();
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(req, sysMenu);

        sysMenu.setOperator(credential.getId());
        if (null == req.getId()) {
            db.insert(sysMenu);
        } else {
            db.update(sysMenu);
        }
    }

    @Override
    public void del(IdReq req) {
        Credential credential = CredentialContextHolder.get();
        int res = db.execute("update tb_sys_menu set operator = ?, deleted = 1 where id = ? and deleted = 0 ", credential.getId(), req.getId());
        if (1 != res) {
            throw new BizException("删除失败");
        }
    }

    @Override
    public List<SysMenuRes> list() {

        List<SysMenuRes> list = db.select(SysMenuRes.class, "select * from tb_sys_menu where parent_id is null and deleted = 0 order by seq");
        if (null == list || list.isEmpty()) {
            return null;
        }
        for (SysMenuRes res : list) {

            res.setChildren(listSubMenu(res));
        }

        return list;
    }

    private List<SysMenuRes> listSubMenu(SysMenuRes res) {
        List<SysMenuRes> list = db.select(SysMenuRes.class, "select * from tb_sys_menu where parent_id = ? and deleted = 0 order by seq", res.getId());
        if (null == list || list.isEmpty()) {
            return null;
        }
        for (SysMenuRes res2 : list) {
            res2.setChildren(listSubMenu(res2));
        }
        return list;
    }
}
