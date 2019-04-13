package com.denghb.simplex.holder;

import lombok.Builder;
import lombok.Data;

/**
 * 当前请求信息
 *
 * @Auther: denghb
 * @Date: 2019/4/13 16:30
 */
@Data
@Builder
public class RequestInfo {

    private Long reqId;

    private String uri;

    private String method;

    private String ip;

    private String userAgent;

    private String accessToken;
}
