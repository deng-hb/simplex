package com.denghb.simplex.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 17:23
 */
@Data
public class SysUserSignLogReq {
    /** IP */
    @ApiModelProperty
    private String ip;

    /** 用户名 */
    private String username;

    /** 浏览器代理 */
    private String userAgent;
}
