package com.denghb.simplex.controller;

import com.denghb.simplex.common.annotation.RequestRateLimit;
import com.denghb.simplex.model.CaptchaRes;
import com.denghb.simplex.common.base.JSONModel;
import com.denghb.simplex.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class BaseController {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping(name = "验证码", value = "/captcha")
    public JSONModel<CaptchaRes> captcha() {
        CaptchaRes res = captchaService.generate();

        return JSONModel.buildSuccessData(res);
    }

    @RequestMapping("/hs")
    public String hs() {
        return "OK";
    }
}
