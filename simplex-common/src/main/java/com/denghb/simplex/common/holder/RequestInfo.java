package com.denghb.simplex.common.holder;

import lombok.Builder;
import lombok.Data;

/**
 * 当前请求信息
 *
 * @author denghb
 * @since 2019/4/13 16:30
 */
@Data
@Builder
public class RequestInfo {

    private String reqId;

    private String uri;

    private String method;

    private String ip;

    private String userAgent;

    private String accessToken;
}
