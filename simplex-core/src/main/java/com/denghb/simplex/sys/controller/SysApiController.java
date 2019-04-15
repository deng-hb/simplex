package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.sys.model.SysApiRes;
import com.denghb.simplex.sys.service.SysApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/16 00:29
 */
@RestController
@RequestMapping("/sys/api")
public class SysApiController {

    @Autowired
    private SysApiService sysApiService;

    @GetMapping("/list")
    public JSONModel<List<SysApiRes>> list() {
        List<SysApiRes> res = sysApiService.list();
        return JSONModel.buildSuccess("ok", res);
    }
}
