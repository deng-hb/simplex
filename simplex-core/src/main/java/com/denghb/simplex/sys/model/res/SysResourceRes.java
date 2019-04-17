package com.denghb.simplex.sys.model.res;

import lombok.Data;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:38
 */
@Data
public class SysResourceRes extends SysBaseRes {

    private Integer id;

    private Integer parentId;

    private String type;

    private String title;

    private String path;

    private String icon;

    private Integer seq;

    private Integer opened;
}
