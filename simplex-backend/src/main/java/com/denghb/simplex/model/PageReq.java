package com.denghb.simplex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PageReq {

    @Range(min = 1, message = "页码错误")
    @ApiModelProperty(value = "页码", example = "1")
    private int page = 1;

    @Range(min = 1, message = "每页数量错误")
    @ApiModelProperty(value = "每页数量", example = "10")
    private int pageSize = 10;

    @ApiModelProperty(value = "排序降序字段", hidden = true)
    private List<String> desc = new ArrayList<String>();

    @ApiModelProperty(value = "排序升序字段", hidden = true)
    private List<String> asc = new ArrayList<String>();

    @ApiModelProperty(value = "可排序字段", hidden = true)
    private List<String> sorts = new ArrayList<String>();

    public void setDefaultDescSort(String column) {
        setDefaultSort(column, false);
    }

    public void setDefaultAscSort(String column) {
        setDefaultSort(column, true);
    }

    private void setDefaultSort(String column, boolean isAsc) {
        if (!asc.isEmpty() || !desc.isEmpty()) {
            return;
        }
        if (!sorts.contains(column)) {
            sorts.add(column);
        }
        if (isAsc) {
            asc.add(column);
        } else {
            desc.add(column);
        }
    }

}
