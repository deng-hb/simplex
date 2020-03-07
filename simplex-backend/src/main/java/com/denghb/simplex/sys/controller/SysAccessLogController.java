package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.model.res.SysAccessLogRes;
import com.denghb.simplex.sys.service.SysAccessLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author denghb
 * @since 2019/4/17 21:58
 */
@Api(tags = "系统访问日志")
@RestController
@RequestMapping("/sys/access/log")
public class SysAccessLogController {

    @Autowired
    private SysAccessLogService sysAccessLogService;

    @ApiOperation("访问列表")
    @PostMapping("/list")
    public JSONModel<PageRes<SysAccessLogRes>> list(@RequestBody PageReq req) {

        PageRes<SysAccessLogRes> res = sysAccessLogService.list(req);
        return JSONModel.buildSuccessData(res);
    }

}
