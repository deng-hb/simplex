package com.denghb.simplex.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysBaseRes {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("操作人姓名")
    private String operatorName;

    @ApiModelProperty("创建时间")
    private java.util.Date createdTime;

    @ApiModelProperty("更新时间")
    private java.util.Date updatedTime;

}
