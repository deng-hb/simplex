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

    @PostMapping(name = "保存角色", value = "/save")
    public JSONModel<String> save(@Valid @RequestBody SysRoleReq req) {
        sysRoleService.save(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "角色列表", value = "/list")
    public JSONModel<PageRes<SysRoleRes>> list(@RequestBody PageReq req) {

        PageRes<SysRoleRes> res = sysRoleService.list(req);
        return JSONModel.buildSuccess("ok", res);
    }

    @GetMapping(name = "角基本信息", value = "/list")
    public JSONModel<List<SysRoleInfoRes>> list() {

        List<SysRoleInfoRes> res = sysRoleService.list();
        return JSONModel.buildSuccess("ok", res);
    }

}
