package com.denghb.simplex.sys.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: denghb
 * @Date: 2019/4/19 21:55
 */
@Data
public class SysUserUpdatePasswordReq {

    @ApiModelProperty("原密码")
    @NotNull(message = "原密码不能为空")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotNull(message = "新密码不能为空")
    private String newPassword;// 前台判断相同
}
