package com.denghb.simplex.config;

import com.denghb.simplex.base.Credential;
import com.denghb.simplex.base.CredentialContext;
import com.denghb.simplex.base.RequestCountContext;
import com.denghb.simplex.sys.model.SysAccessLogReq;
import com.denghb.simplex.sys.service.AuthAccessService;
import com.denghb.simplex.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
public class AuthAccessFilter implements Filter {

    private long count = 0;

    private static Pattern IGNORE_EXT = Pattern.compile(".*(.html|.png|.svg|.txt|.js|.css|.ico|.ttf|.eot|.woff)$", Pattern.CASE_INSENSITIVE);

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
        String url = uri;
        String query = req.getQueryString();
        if (null != query) {
            url += '?' + query;
        }

        String ip = WebUtils.getIpAddr(req);
        String method = req.getMethod();
        String userAgent = req.getHeader("User-Agent");
        String accessToken = req.getHeader("X-Access-Token");

        log.info("req:{},{},{},{},{}", count, ip, method, uri, accessToken);

        int id = authAccessService.addLog(SysAccessLogReq.builder().ip(ip).method(method).url(url).accessToken(accessToken).userAgent(userAgent).build());

        if (IGNORE_EXT.matcher(uri).matches()) {

            // 放行
            chain.doFilter(request, response);
            authAccessService.setEndTime(id);
            return;// *THE END
        }
        RequestCountContext.set(count);

        if (!authAccessService.isOpened(method, uri)) {

            Credential credential = authAccessService.validate(accessToken, ip, userAgent);
            if (null == credential) {
                res.setHeader("Content-Type", "application/json;charset=utf-8");
                String result = "{\"code\":2,\"msg\":\"登录过期或未登录\"}";
                res.getWriter().write(result);

                log.info("req:{},res:{}", RequestCountContext.get(), result);
                RequestCountContext.remove();
                authAccessService.setEndTime(id);
                return;// *THE END
            }
            CredentialContext.set(credential);
        }

        // 放行
        chain.doFilter(request, response);

        CredentialContext.remove();
        RequestCountContext.remove();
        authAccessService.setEndTime(id);
    }

    @Override
    public void destroy() {

    }
}
