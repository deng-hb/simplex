package com.denghb.simplex.sys.model.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SysAccessLogRes extends SysBaseRes {

    @ApiModelProperty("大写")
    private String method;

    @ApiModelProperty("路径带参数")
    private String url;

    private String accessToken;

    private String ip;

    private String userAgent;

    private Date startTime;

    private Date endTime;
}
