package com.denghb.simplex.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.denghb.eorm.Eorm;
import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.base.AuthException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.model.CaptchaRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = com.denghb.simplex.App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseTest {

    protected static String USER_AGENT = "Mozilla/5.0 TEST";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected Eorm db;

    /**
     * 获取有效的Token用于测试
     */
    protected String getAccessToken() {
        String sql = "select access_token from tb_sys_user_token where expire_time > now() limit 1";
        String accessToken = db.selectOne(String.class, sql);
        if (StringUtils.isBlank(accessToken)) {
            throw new AuthException("暂无有效Access Token");
        }
        return accessToken;
    }

    @Before
    public void before() {
        log.info("before");
    }

    @After
    public void after() {
        log.info("after");
    }

    protected void initTestCredential() {

        Credential credential = new Credential();
        credential.setId(100001);
        credential.setName("管理员");
        credential.setUsername("su");
        CredentialContextHolder.set(credential);
    }


    @Test
    public void captcha() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/captcha");
        builder.header("User-Agent", USER_AGENT);
        String result = mockMvc.perform(builder).andDo(MockMvcResultHandlers.log()).andReturn().getResponse().getContentAsString();

        JSONModel<CaptchaRes> res = JSON.parseObject(result, new TypeReference<JSONModel<CaptchaRes>>() {
        });

        Assert.assertEquals(1, res.getCode());
    }
}
