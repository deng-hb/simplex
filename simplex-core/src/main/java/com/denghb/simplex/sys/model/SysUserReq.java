package com.denghb.simplex.sys.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SysUserReq {

    @NotNull
    private Integer age;

    @NotNull
    private String name;
}
