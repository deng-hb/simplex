package com.denghb.simplex.sys.model.req;

import lombok.Data;

/**
 * @author denghb
 * @since 2019/4/13 21:38
 */
@Data
public class SysResourceQueryReq {

    private String type;

    private String title;

    private String path;

    private Integer opened;
}
