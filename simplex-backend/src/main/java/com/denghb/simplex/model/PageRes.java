package com.denghb.simplex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageRes<T> {

    @ApiModelProperty("列表")
    private List<T> list;

    @ApiModelProperty("总数")
    private long total;

    public PageRes() {
    }

    public PageRes(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }
}
