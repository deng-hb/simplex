package com.denghb.simplex.sys.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author denghb
 * @since 2019/4/13 17:23
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
