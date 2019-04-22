package com.denghb.simplex.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.denghb.simplex.base.Consts;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.sys.model.req.SysUserReq;
import com.denghb.simplex.sys.model.res.SysUserSignInRes;
import com.denghb.simplex.sys.service.SysUserService;
import com.denghb.simplex.test.BaseTest;
import com.denghb.simplex.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 15:17
 */
@Slf4j
public class SysUserServiceTest extends BaseTest {


    @Autowired
    private SysUserService sysUserService;

    @Test
    public void save() throws Exception {

        SysUserReq req = new SysUserReq();
        req.setName("超级管理员");
        req.setUsername("su");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/sys/user/save");
        builder.header("User-Agent", USER_AGENT);
        builder.header(Consts.ACCESS_TOKEN, getAccessToken());
        builder.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        builder.content(JSON.toJSONString(req));

        String result = mockMvc.perform(builder).andDo(MockMvcResultHandlers.log()).andReturn().getResponse().getContentAsString();

        JSONModel<String> res = JSON.parseObject(result, new TypeReference<JSONModel<String>>() {
        });

        Assert.assertEquals(1, res.getCode());
    }

    @Test
    public void signIn() {
        String username = "su";
        String password = "P2V49fAp";
        password = Md5Utils.md5(password);
        password = Md5Utils.md5(password);

        SysUserSignInRes res = sysUserService.signIn(username, password, "1", "123");

        log.info("{}", res);
    }
}
