package com.denghb.simplex.sys.model.req;

import lombok.Data;

import java.util.List;

/**
 * @author denghb
 * @since 2019/4/17 21:19
 */
@Data
public class SysRoleReq {

    private Integer id;

    private String name;

    private String description;

    private List<Integer> sysResourceIds;
}
