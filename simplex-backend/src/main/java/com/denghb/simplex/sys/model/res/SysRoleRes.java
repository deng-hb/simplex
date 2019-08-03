package com.denghb.simplex.sys.model.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author denghb
 * @since 2019/4/17 21:20
 */
@Data
public class SysRoleRes extends SysBaseRes {

    private String name;

    private String description;

    @ApiModelProperty("操作人姓名")
    private String operatorName;
}
