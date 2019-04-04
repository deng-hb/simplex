package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.service.CaptchaService;
import com.denghb.simplex.sys.model.SysUserReq;
import com.denghb.simplex.sys.model.SysUserSignInReq;
import com.denghb.simplex.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/save")
    public JSONModel<String> save(@Valid @RequestBody SysUserReq req) {
        sysUserService.save();
        return JSONModel.buildSuccess("ok");
    }
}


