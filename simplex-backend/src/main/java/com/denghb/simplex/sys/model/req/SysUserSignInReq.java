package com.denghb.simplex.sys.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserSignInReq {

    @ApiModelProperty(value = "账号", required = true)
    private String username;

    @ApiModelProperty(value = "密码（提交前MD5一次）", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    private String key;

    @ApiModelProperty(value = "验证码", required = true)
    private String code;
}
