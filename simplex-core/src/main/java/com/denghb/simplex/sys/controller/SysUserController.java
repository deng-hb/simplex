package com.denghb.simplex.sys.controller;

import com.denghb.simplex.model.JSONModel;
import com.denghb.simplex.sys.model.SysUserReq;
import com.denghb.simplex.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/aaa")
    public JSONModel<String> aaa(@RequestParam(value = "a", required = false) Integer a) {
        if (null != a) {
            int b = a / 0;
        }

        return JSONModel.buildSuccess("aaa");
    }

    @PostMapping("/save")
    public JSONModel<String> save(@Valid @RequestBody SysUserReq req) {
        sysUserService.save();
        return JSONModel.buildSuccess("ok");
    }
}


