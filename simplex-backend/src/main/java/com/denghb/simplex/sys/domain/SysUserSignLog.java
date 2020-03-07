package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 用户登录日志
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_user_sign_log")
public class SysUserSignLog implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** IP */
    @Ecolumn(name = "ip")
    private String ip;

    /** 用户名 */
    @Ecolumn(name = "username")
    private String username;

    /** 浏览器代理 */
    @Ecolumn(name = "user_agent")
    private String userAgent;

    /** 1成功,0失败 */
    @Ecolumn(name = "status")
    private Integer status;

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