package com.denghb.simplex.config;

import com.denghb.simplex.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthAccessFilter implements Filter {

    private long count = 0;

    private ThreadLocal<Long> ss = new InheritableThreadLocal<>();

    public AuthAccessFilter() {
        log.info("AuthAccessFilter init");
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ss.set(++count);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String ip = WebUtils.getIpAddr(req);
        String method = req.getMethod();

        if (uri.startsWith("/aa/")) {
            String accessToken = req.getHeader("x-access-token");
//            CurrentUser cu = sysUserService.validateAccessToken(accessToken);
//            if (null == cu) {
                res.setHeader("Content-Type", "application/json;charset=utf-8");
                res.getWriter().write("{\"code\":2,\"msg\":\"登录过期或未登录\"}");
                ss.remove();
                return;
//            }
//            CurrentUserContext.set(cu);
        }
        log.info("{},req:{},{}", ip, count, uri);
        chain.doFilter(request, response);
        log.info("{},res:{},{}", ip, ss.get(), uri);
//        CurrentUserContext.remove();

        ss.remove();
    }

    @Override
    public void destroy() {

    }
}
