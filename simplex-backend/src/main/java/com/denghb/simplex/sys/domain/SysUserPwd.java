package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统用户密码
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_user_pwd")
public class SysUserPwd implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** 用户ID */
    @Ecolumn(name = "sys_user_id")
    private Integer sysUserId;

    /** 密码 */
    @Ecolumn(name = "pwd")
    private String pwd;

    /** 盐 */
    @Ecolumn(name = "salt")
    private String salt;

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