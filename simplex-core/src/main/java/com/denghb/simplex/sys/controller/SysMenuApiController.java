package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.model.SysApiRes;
import com.denghb.simplex.sys.service.SysMenuApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:50
 */
@RestController
@RequestMapping("/sys/menu/api")
public class SysMenuApiController {

    @Autowired
    private SysMenuApiService sysMenuApiService;

    @PostMapping(name = "为菜单分配接口", value = "save")
    public JSONModel<String> save(@RequestParam("menuId") int menuId, @Valid @RequestBody List<IdReq> reqList) {
        sysMenuApiService.save(menuId, reqList);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping(name = "为菜单移除接口", value = "del")
    public JSONModel<String> del(@RequestParam("menuId") int menuId, @Valid @RequestBody List<IdReq> reqList) {
        sysMenuApiService.del(menuId, reqList);
        return JSONModel.buildSuccess("操作成功");
    }

    @GetMapping(name = "当前菜单下的接口", value = "list")
    public JSONModel<List<SysApiRes>> list(@RequestParam("menuId") int menuId) {
        List<SysApiRes> res = sysMenuApiService.list(menuId);
        return JSONModel.buildSuccess("ok", res);
    }
}
