package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.BizException;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.holder.RequestInfoContextHolder;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.service.CaptchaService;
import com.denghb.simplex.sys.model.*;
import com.denghb.simplex.sys.service.SysUserService;
import com.denghb.simplex.sys.service.SysUserSignLogService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private SysUserSignLogService sysUserSignLogService;

    @PostMapping("/save")
    public JSONModel<String> save(@Valid @RequestBody SysUserReq req) {
        sysUserService.save(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping("/del")
    public JSONModel<String> del(@Valid @RequestBody IdReq req) {
        sysUserService.del(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping("/list")
    public JSONModel<PageRes<SysUserRes>> list(@Valid @RequestBody PageReq req) {
        PageRes<SysUserRes> res = sysUserService.list(req);
        return JSONModel.buildSuccess("ok", res);
    }

    @PostMapping("/signIn")
    public JSONModel<SysUserSignInRes> signIn(@Valid @RequestBody SysUserSignInReq req) {

        boolean valid = captchaService.validate(req.getKey(), req.getCode());
        if (valid) {
            throw new BizException("验证码输入有误");
        }

        SysUserSignLogReq logReq = new SysUserSignLogReq();
        BeanUtils.copyProperties(RequestInfoContextHolder.get(), logReq);
        logReq.setUsername(req.getUsername());
        int id = sysUserSignLogService.add(logReq);
        RequestInfo requestInfo = RequestInfoContextHolder.get();

        SysUserSignInRes res = sysUserService.signIn(req.getUsername(), req.getPassword(), requestInfo.getIp(), requestInfo.getUserAgent());

        sysUserSignLogService.setSuccess(id);
        return JSONModel.buildSuccess("ok", res);
    }

    @PostMapping("/unlockSignError")
    public JSONModel<String> unlockSignError(@Valid @RequestBody IdReq req) {
        sysUserService.unlockSignError(req);
        return JSONModel.buildSuccess("操作成功");
    }
}


