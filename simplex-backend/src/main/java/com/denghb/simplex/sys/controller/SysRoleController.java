package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.model.req.SysRoleReq;
import com.denghb.simplex.sys.model.res.SysRoleInfoRes;
import com.denghb.simplex.sys.model.res.SysRoleRes;
import com.denghb.simplex.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author denghb
 * @since 2019/4/17 21:58
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping(name = "创建角色", value = "/add")
    public JSONModel<String> add(@Valid @RequestBody SysRoleReq req) {
        sysRoleService.doEdit(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "编辑角色", value = "/edit")
    public JSONModel<String> edit(@Valid @RequestBody SysRoleReq req) {
        sysRoleService.doEdit(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "删除角色", value = "/del")
    public JSONModel<String> del(@Valid @RequestBody IdReq req) {
        sysRoleService.doDel(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "角色列表", value = "/list")
    public JSONModel<PageRes<SysRoleRes>> list(@RequestBody PageReq req) {

        PageRes<SysRoleRes> res = sysRoleService.list(req);
        return JSONModel.buildSuccess("ok", res);
    }

    @PostMapping(name = "设置角色资源", value = "/setSysResourceIds")
    public JSONModel<String> setSysResourceIds(@RequestParam("roleId") int roleId, @RequestBody List<Integer> sysResourceIds) {
        sysRoleService.setSysResourceIds(roleId, sysResourceIds);
        return JSONModel.buildSuccess("操作成功");
    }

    @GetMapping(name = "获取角色资源", value = "/listSysResourceId")
    public JSONModel<List<Integer>> listSysResourceId(@RequestParam("roleId") int roleId) {
        List<Integer> list = sysRoleService.listSysResourceId(roleId);
        return JSONModel.buildSuccess("ok", list);
    }

    @GetMapping(name = "角基本信息", value = "/list")
    public JSONModel<List<SysRoleInfoRes>> list() {

        List<SysRoleInfoRes> res = sysRoleService.list();
        return JSONModel.buildSuccess("ok", res);
    }

}
