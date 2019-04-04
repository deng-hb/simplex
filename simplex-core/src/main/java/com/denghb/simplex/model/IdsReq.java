package com.denghb.simplex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@ToString
public class IdsReq {

    @NotEmpty
    @ApiModelProperty(value = "IDs", required = true)
    private List<Integer> ids;

}
