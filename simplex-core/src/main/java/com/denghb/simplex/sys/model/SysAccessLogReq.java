package com.denghb.simplex.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SysAccessLogReq {

    @ApiModelProperty("大写")
    private String method;

    @ApiModelProperty("路径带参数")
    private String url;

    private String accessToken;

    private String ip;

    private String userAgent;
}
