package com.denghb.simplex.sys.model.res;

import lombok.Data;

/**
 * @author denghb
 * @since 2019/4/13 21:38
 */
@Data
public class SysResourceRes extends SysBaseRes {

    private Integer id;

    private Integer parentId;

    private String type;

    private String title;

    private String uri;

    private String icon;

    private String method;

    private Integer seq;

    private Integer opened;
}
