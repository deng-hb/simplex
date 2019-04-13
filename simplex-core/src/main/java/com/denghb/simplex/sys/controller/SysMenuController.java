package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.model.SysMenuReq;
import com.denghb.simplex.sys.model.SysMenuRes;
import com.denghb.simplex.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:50
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/save")
    public JSONModel<String> save(@Valid @RequestBody SysMenuReq req) {
        sysMenuService.save(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping("/del")
    public JSONModel<String> del(@Valid @RequestBody IdReq req) {
        sysMenuService.del(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping("/list")
    public JSONModel<List<SysMenuRes>> list() {
        List<SysMenuRes> res = sysMenuService.list();
        return JSONModel.buildSuccess("ok", res);
    }
}
