package com.denghb.simplex.sys.model;

import lombok.Data;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 15:04
 */
@Data
public class SysUserRes {

    private Integer id;

    /** 账号 */
    private String username;

    /** 姓名 */
    private String name;
}
