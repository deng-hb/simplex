package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统角色
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_role")
public class SysRole implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** 代码（唯一） */
    @Ecolumn(name = "code")
    private String code;

    /** 角色名称 */
    @Ecolumn(name = "name")
    private String name;

    /** 描述 */
    @Ecolumn(name = "description")
    private String description;

    /** 操作人 */
    @Ecolumn(name = "operator")
    private Integer operator;

    /** 创建时间 */
    @Ecolumn(name = "created_time")
    private java.util.Date createdTime;

    /** 更新时间 */
    @Ecolumn(name = "updated_time")
    private java.util.Date updatedTime;

    /** 逻辑删除 */
    @Ecolumn(name = "deleted")
    private Integer deleted;

    /** 版本号 */
    @Ecolumn(name = "version")
    private Integer version;


}