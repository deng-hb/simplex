package com.denghb.simplex.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SysUserReq {

    @ApiModelProperty("id<=0为创建，其他为编辑")
    private Integer id;

    @NotNull
    private Integer age;

    @NotNull
    private String name;
}
