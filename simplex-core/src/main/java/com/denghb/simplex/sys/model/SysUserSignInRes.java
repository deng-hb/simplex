package com.denghb.simplex.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserSignInRes {

    @ApiModelProperty(value = "令牌")
    private String token;
}
