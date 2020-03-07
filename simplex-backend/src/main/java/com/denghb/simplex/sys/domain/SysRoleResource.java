package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统角色资源
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_role_resource")
public class SysRoleResource implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** 角色ID */
    @Ecolumn(name = "sys_role_id")
    private Integer sysRoleId;

    /** 资源ID */
    @Ecolumn(name = "sys_resource_id")
    private Integer sysResourceId;

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