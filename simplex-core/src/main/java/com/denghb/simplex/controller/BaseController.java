package com.denghb.simplex.controller;

import com.denghb.simplex.model.CaptchaRes;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.service.CaptchaService;
import com.denghb.simplex.sys.model.SysUserSignInReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BaseController {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/captcha")
    public JSONModel<CaptchaRes> captcha() {
        CaptchaRes res = captchaService.generate();

        return JSONModel.buildSuccess("ok", res);
    }

    @PostMapping("/signIn")
    public JSONModel<String> signIn(@Valid @RequestBody SysUserSignInReq req) {

        boolean valid = captchaService.validate(req.getKey(), req.getCode());
        if (valid) {
            return JSONModel.buildFailure("验证码校验失败");
        }

        return JSONModel.buildSuccess("aaa");
    }
}
