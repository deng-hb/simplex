package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统用户
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_user")
public class SysUser implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** 账号 */
    @Ecolumn(name = "username")
    private String username;

    /** 姓名 */
    @Ecolumn(name = "name")
    private String name;

    /** 生日 */
    @Ecolumn(name = "birthday")
    private java.util.Date birthday;

    /** 邮箱 */
    @Ecolumn(name = "email")
    private String email;

    /** 状态1正常，2:冻结 */
    @Ecolumn(name = "status")
    private Integer status;

    /** 登录错误次数 */
    @Ecolumn(name = "sign_error_size")
    private Integer signErrorSize;

    /** 登录错误上限 */
    @Ecolumn(name = "sign_error_limit")
    private Integer signErrorLimit;

    /** 角色ID */
    @Ecolumn(name = "sys_role_id")
    private Integer sysRoleId;

    /** 操作人 */
    @Ecolumn(name = "operator")
    private Integer operator;

    /** 插入时间 */
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