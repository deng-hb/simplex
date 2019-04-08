package com.denghb.simplex;


import lombok.extern.slf4j.Slf4j;
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
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/captcha");

        mockMvc.perform(builder).andDo(MockMvcResultHandlers.log());
//        String res = mockMvc.perform(builder).andReturn().getResponse().getContentAsString();
//        log.info(res);

    }
}
