package com.denghb.simplex.sys.model;

import lombok.Data;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:38
 */
@Data
public class SysMenuReq {

    private Integer id;

    private Integer parentId;

    private String title;

    private String path;

    private String icon;

    private Integer seq;
}
