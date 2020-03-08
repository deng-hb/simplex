package com.denghb.simplex.sys.controller;

import com.denghb.simplex.common.annotation.RequestRateLimit;
import com.denghb.simplex.common.annotation.RequestResubmitLimit;
import com.denghb.simplex.common.base.BizException;
import com.denghb.simplex.common.base.JSONModel;
import com.denghb.simplex.common.holder.CredentialContextHolder;
import com.denghb.simplex.common.holder.RequestInfo;
import com.denghb.simplex.common.holder.RequestInfoContextHolder;
import com.denghb.simplex.common.model.IdReq;
import com.denghb.simplex.common.model.PageReq;
import com.denghb.simplex.common.model.PageRes;
import com.denghb.simplex.service.CaptchaService;
import com.denghb.simplex.sys.model.req.SysUserReq;
import com.denghb.simplex.sys.model.req.SysUserSignInReq;
import com.denghb.simplex.sys.model.req.SysUserSignLogReq;
import com.denghb.simplex.sys.model.req.SysUserUpdatePasswordReq;
import com.denghb.simplex.sys.model.res.SysMenuRes;
import com.denghb.simplex.sys.model.res.SysUserRes;
import com.denghb.simplex.sys.model.res.SysUserSignInRes;
import com.denghb.simplex.sys.service.SysUserService;
import com.denghb.simplex.sys.service.SysUserSignLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "系统用户管理")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserSignLogService sysUserSignLogService;

    @ApiOperation("保存用户")
    @PostMapping("/save")
    public JSONModel<String> save(@Valid @RequestBody SysUserReq req) {
        sysUserService.save(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @ApiOperation("删除用户")
    @PostMapping("/del")
    public JSONModel<String> del(@Valid @RequestBody IdReq req) {
        sysUserService.del(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @ApiOperation("用户列表")
    @PostMapping("/list")
    public JSONModel<PageRes<SysUserRes>> list(@Valid @RequestBody PageReq req) {
        PageRes<SysUserRes> res = sysUserService.list(req);
        return JSONModel.buildSuccessData(res);
    }

    @RequestRateLimit(name = "signIn", count = 1, period = 3)
    @RequestResubmitLimit(name = "signIn", fields = {"username"})
    @ApiOperation("登录")
    @PostMapping("/signIn")
    public JSONModel<SysUserSignInRes> signIn(@Valid @RequestBody SysUserSignInReq req) {

        boolean valid = captchaService.validate(req.getKey(), req.getCode());
        if (!valid) {
            throw new BizException("验证码输入有误");
        }

        SysUserSignLogReq logReq = new SysUserSignLogReq();
        BeanUtils.copyProperties(RequestInfoContextHolder.get(), logReq);
        logReq.setUsername(req.getUsername());
        int id = sysUserSignLogService.add(logReq);
        RequestInfo requestInfo = RequestInfoContextHolder.get();

        SysUserSignInRes res = sysUserService.signIn(req.getUsername(), req.getPassword(), requestInfo.getIp(), requestInfo.getUserAgent());

        sysUserSignLogService.setSuccess(id);
        return JSONModel.buildSuccessData(res);
    }

    @ApiOperation("解锁用户登录")
    @PostMapping("/unlockSignError")
    public JSONModel<String> unlockSignError(@Valid @RequestBody IdReq req) {
        sysUserService.unlockSignError(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @ApiOperation("用户菜单")
    @GetMapping("/menu")
    public JSONModel<List<SysMenuRes>> menu() {
        List<SysMenuRes> res = sysUserService.menu();
        return JSONModel.buildSuccessData(res);
    }

    @ApiOperation("用户接口")
    @GetMapping("/api")
    public JSONModel<List<String>> api() {
        List<String> res = sysUserService.api();
        return JSONModel.buildSuccessData(res);
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public JSONModel<String> updatePassword(@Valid @RequestBody SysUserUpdatePasswordReq req) {
        sysUserService.updatePassword(req);
        return JSONModel.buildSuccess("修改成功，请重新登录");
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public JSONModel<String> resetPassword(@Valid @RequestBody IdReq req) {
        sysUserService.resetPassword(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @ApiOperation("禁用用户")
    @PostMapping("/disabled")
    public JSONModel<String> disabled(@Valid @RequestBody IdReq req) {
        sysUserService.disabled(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @ApiOperation("启用用户")
    @PostMapping("/enabled")
    public JSONModel<String> enabled(@Valid @RequestBody IdReq req) {
        sysUserService.enabled(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @ApiOperation("安全登出")
    @GetMapping("/signOut")
    public JSONModel<String> signOut() {
        int sysUserId = CredentialContextHolder.get().getId();
        sysUserService.signOut(sysUserId);
        return JSONModel.buildSuccess("登出成功");
    }
}


