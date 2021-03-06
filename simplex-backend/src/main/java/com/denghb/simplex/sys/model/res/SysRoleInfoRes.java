package com.denghb.simplex.sys.model.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author denghb
 * @since 2019/4/17 21:20
 */
@Data
public class SysRoleInfoRes {

    @ApiModelProperty("ID")
    private Integer id;

    private String name;
}
