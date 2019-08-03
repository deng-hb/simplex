package com.denghb.simplex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data
@ToString
public class IdReq {

    @Range(min = 1, message = "ID错误")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

}
