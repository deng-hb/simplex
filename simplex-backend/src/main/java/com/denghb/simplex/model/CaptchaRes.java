package com.denghb.simplex.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "验证码返回结果")
@Data
public class CaptchaRes {

    @ApiModelProperty("验证码校验代码")
    private String key;

    @ApiModelProperty("验证码Base64")
    private String imageData;
}
