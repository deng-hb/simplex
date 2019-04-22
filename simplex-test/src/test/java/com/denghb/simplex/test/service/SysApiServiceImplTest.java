package com.denghb.simplex.test.service;

import com.denghb.simplex.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.*;

/**
 * @Auther: denghb
 * @Date: 2019/4/15 22:19
 */
@Slf4j
public class SysApiServiceImplTest extends BaseTest {

    @Autowired
    public void printMapping(List<RequestMappingInfoHandlerMapping> handlerMappingList) {

        initTestCredential();

        for (RequestMappingInfoHandlerMapping mapping : handlerMappingList) {
            Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
            for (RequestMappingInfo req : map.keySet()) {
                Set<RequestMethod> requestMethods;
                if (0 == req.getMethodsCondition().getMethods().size()) {
                    // TODO
                    requestMethods = new HashSet<>(Arrays.asList(RequestMethod.values()));
                } else {
                    requestMethods = req.getMethodsCondition().getMethods();
                }
                for (RequestMethod method : requestMethods) {
                    String uri = req.getPatternsCondition().getPatterns().iterator().next();
                    save(req.getName(), String.valueOf(method), uri);
                }
            }
        }
    }

    private void save(String description, String method, String uri) {

        log.info("{},{},{}", description, method, uri);
    }

}