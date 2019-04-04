package com.denghb.simplex.controller;

import com.denghb.simplex.model.CaptchaRes;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BaseController {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/captcha")
    public JSONModel<CaptchaRes> captcha() {
        CaptchaRes res = captchaService.generate();

        return JSONModel.buildSuccess("ok", res);
    }
}
