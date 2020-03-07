package com.denghb.simplex.config;

import com.denghb.simplex.base.Consts;
import com.denghb.simplex.holder.RequestInfo;
import com.denghb.simplex.holder.RequestInfoContextHolder;
import com.denghb.simplex.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 记录基础请求信息
 */
@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

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
                .reqId(UUID.randomUUID().toString())
                .build();

        log.info("ReqId:{},url:{},start", requestInfo.getReqId(), url);
        RequestInfoContextHolder.set(requestInfo);

        filterChain.doFilter(request, response);

        RequestInfoContextHolder.reset();
        log.info("ReqId:{},{}ms,end", requestInfo.getReqId(), (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
