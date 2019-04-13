package com.denghb.simplex.config;

import com.alibaba.fastjson.JSON;
import com.denghb.simplex.base.Consts;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.holder.RequestInfoContextHolder;
import com.denghb.simplex.sys.model.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import com.denghb.simplex.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class AuthAccessFilter implements Filter {

    private final static AtomicLong REQUEST_COUNT = new AtomicLong(100000000);

    private AuthAccessService authAccessService;

    public AuthAccessFilter(AuthAccessService authAccessService) {
        log.info("AuthAccessFilter init");
        this.authAccessService = authAccessService;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String url = uri;
        String query = req.getQueryString();
        if (null != query) {
            url += '?' + query;
        }

        String ip = WebUtils.getIpAddr(req);
        String method = req.getMethod();
        String userAgent = req.getHeader("User-Agent");
        String accessToken = req.getHeader(Consts.ACCESS_TOKEN);

        RequestInfo requestInfo = RequestInfo.builder()
                .ip(ip)
                .uri(uri)
                .method(method)
                .userAgent(userAgent)
                .accessToken(accessToken)
                .reqId(REQUEST_COUNT.incrementAndGet())
                .build();

        log.info("reqId:{},start,{}", requestInfo.getReqId(), JSON.toJSONString(requestInfo));
        RequestInfoContextHolder.set(requestInfo);

        SysAccessLogReq logReq = SysAccessLogReq.builder()
                .ip(ip)
                .url(url)
                .method(method)
                .userAgent(userAgent)
                .accessToken(accessToken)
                .build();
        int id = authAccessService.addLog(logReq);

        if (!authAccessService.isOpened(method, uri)) {

            Credential credential = authAccessService.validate(requestInfo);
            if (null == credential) {
                res.setHeader("Content-Type", "application/json;charset=utf-8");
                String result = "{\"code\":2,\"msg\":\"登录过期或未登录\"}";
                res.getWriter().write(result);

                log.info("reqId:{},response:{}", requestInfo.getReqId(), result);
                RequestInfoContextHolder.reset();
                authAccessService.setEndTime(id);

                log.info("reqId:{},end,{}ms", requestInfo.getReqId(), (System.currentTimeMillis() - start));
                return;// *THE END
            }
            CredentialContextHolder.set(credential);

        }

        // 放行
        chain.doFilter(request, response);

        CredentialContextHolder.reset();
        RequestInfoContextHolder.reset();
        authAccessService.setEndTime(id);

        log.info("reqId:{},end,{}ms", requestInfo.getReqId(), (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
