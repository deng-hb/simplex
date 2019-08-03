package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.Consts;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.consts.SysResourceConsts;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.model.req.SysResourceQueryReq;
import com.denghb.simplex.sys.model.req.SysResourceReq;
import com.denghb.simplex.sys.model.res.SysResourceRes;
import com.denghb.simplex.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author denghb
 * @since 2019/4/13 21:50
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @GetMapping(name = "资源类型", value = "/type")
    public JSONModel<Map<Object, String>> type() {

        Map<Object, String> list = Consts.map(SysResourceConsts.Type.class);
        return JSONModel.buildSuccess("ok", list);
    }

    @PostMapping(name = "保存资源", value = "/save")
    public JSONModel<String> save(@Valid @RequestBody SysResourceReq req) {
        sysResourceService.save(req);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "删除资源", value = "/del")
    public JSONModel<String> del(@Valid @RequestBody IdReq req) {
        sysResourceService.del(req.getId());
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "资源列表", value = "/list")
    public JSONModel<List<SysResourceRes>> list(@RequestBody SysResourceQueryReq req) {
        List<SysResourceRes> res = sysResourceService.list(req);
        return JSONModel.buildSuccess("ok", res);
    }
}
