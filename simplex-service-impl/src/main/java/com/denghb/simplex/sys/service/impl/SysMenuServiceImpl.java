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

import java.util.ArrayList;
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
        List<SysMenuRes> list = db.select(SysMenuRes.class, "select * from tb_sys_menu where deleted = 0 order by parent_id, seq asc");
        if (null == list || list.isEmpty()) {
            return null;
        }
        List<SysMenuRes> menu = new ArrayList<>();
        for (SysMenuRes res : list) {
            if (null == res.getParentId()) {
                menu.add(res);
                findChildren(res, list);
            }
        }

        return menu;
    }

    @Override
    public void findChildren(SysMenuRes res, List<SysMenuRes> list) {
        if (null == res.getChildren()) {
            res.setChildren(new ArrayList<SysMenuRes>());
        }
        for (SysMenuRes res2 : list) {
            if (res.getId().equals(res2.getParentId())) {
                res.getChildren().add(res2);
                findChildren(res2, list);
            }

        }
    }
}
