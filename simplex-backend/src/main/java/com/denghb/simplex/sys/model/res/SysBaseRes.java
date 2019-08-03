package com.denghb.simplex.sys.model.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysBaseRes {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("创建时间")
    private java.util.Date createdTime;

    @ApiModelProperty("更新时间")
    private java.util.Date updatedTime;

}
