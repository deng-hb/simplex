package com.denghb.simplex.sys.model;

import lombok.Data;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:38
 */
@Data
public class SysMenuRes {

    private Integer id;

    private Integer parentId;

    private String title;

    private String path;

    private String icon;

    private List<SysMenuRes> children;

    private Integer seq;
}
