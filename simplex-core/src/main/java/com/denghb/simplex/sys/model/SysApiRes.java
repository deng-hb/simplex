package com.denghb.simplex.sys.model;

import lombok.Data;

/**
 * @Auther: denghb
 * @Date: 2019/4/15 22:13
 */
@Data
public class SysApiRes {

    private Integer id;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 路径（唯一）
     */
    private String uri;

    /**
     * 描述
     */
    private String description;

    /**
     * 未登录访问（1:开,0:关）
     */
    private Integer opened;
}
