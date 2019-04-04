package com.denghb.simplex.config;

import com.denghb.simplex.base.Credential;
import com.denghb.simplex.sys.service.AuthAccessService;
import com.denghb.simplex.sys.service.SysUserService;
import com.denghb.simplex.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthAccessFilter implements Filter {

    private long count = 0;

    private final ThreadLocal<Long> localCount = new InheritableThreadLocal<>();

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
        ++count;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String ip = WebUtils.getIpAddr(req);
        String method = req.getMethod();

        log.info("{},req:{},{}", ip, count, uri);
        localCount.set(count);

        if (authAccessService.isOpened(method, uri)) {
            String userAgent = req.getHeader("User-Agent");
            String accessToken = req.getHeader("X-Access-Token");

            Credential credential = authAccessService.validate(accessToken, ip, userAgent);
            if (null == credential) {
                res.setHeader("Content-Type", "application/json;charset=utf-8");
                res.getWriter().write("{\"code\":2,\"msg\":\"登录过期或未登录\"}");

                log.info("{},res:{},{}", ip, localCount.get(), uri);
                localCount.remove();
                return;
            }
            Credential.set(credential);
        }


        chain.doFilter(request, response);
        log.info("{},res:{},{}", ip, localCount.get(), uri);
        Credential.remove();

        localCount.remove();
    }

    @Override
    public void destroy() {

    }
}
