package com.denghb.simplex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class PageReq {

    @Range(min = 1, message = "页码错误")
    @ApiModelProperty(value = "页码", example = "1")
    private int page = 1;

    @Range(min = 1, message = "每页数量错误")
    @ApiModelProperty(value = "每页数量", example = "10")
    private int pageSize = 10;

    @ApiModelProperty(value = "分页开始", hidden = true)
    private int pageStart;

    @ApiModelProperty(value = "排序降序字段", hidden = true)
    private Set<String> desc = new HashSet<String>();

    @ApiModelProperty(value = "排序升序字段", hidden = true)
    private Set<String> asc = new HashSet<String>();

    @ApiModelProperty(value = "可排序字段", hidden = true)
    private Set<String> sorts = new HashSet<String>();

    /**
     * 计算
     *
     * @return pageStart
     */
    public int getPageStart() {
        return (page - 1) * pageSize;
    }

}
