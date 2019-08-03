package com.denghb.simplex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data
@ToString
public class PageReq {

    @Range(min = 1, message = "页码错误")
    @ApiModelProperty(value = "页码", example = "1")
    private long page = 1;

    @Range(min = 1, message = "每页数量错误")
    @ApiModelProperty(value = "每页数量", example = "10")
    private long pageSize = 10;
}
