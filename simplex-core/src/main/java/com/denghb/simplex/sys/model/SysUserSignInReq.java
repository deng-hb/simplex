package com.denghb.simplex.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserSignInReq {

    @ApiModelProperty(value = "账号", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    private String captcha;

    @ApiModelProperty(value = "验证码", required = true)
    private String captchaKey;
}
