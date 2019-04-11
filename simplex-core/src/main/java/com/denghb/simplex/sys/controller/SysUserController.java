package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.BizException;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.service.CaptchaService;
import com.denghb.simplex.sys.model.SysUserReq;
import com.denghb.simplex.sys.model.SysUserSignInReq;
import com.denghb.simplex.sys.model.SysUserSignInRes;
import com.denghb.simplex.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/signIn")
    public JSONModel<SysUserSignInRes> signIn(@Valid @RequestBody SysUserSignInReq req) {

        boolean valid = captchaService.validate(req.getKey(), req.getCode());
        if (valid) {
            throw new BizException("验证码校验失败");
        }
        SysUserSignInRes res = sysUserService.signIn(req.getUsername(), req.getPassword());
        return JSONModel.buildSuccess("ok", res);
    }

    @PostMapping("/save")
    public JSONModel<String> save(@Valid @RequestBody SysUserReq req) {
        sysUserService.save(req);
        return JSONModel.buildSuccess("操作成功");
    }
}


