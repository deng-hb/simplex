package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统用户令牌
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_user_token")
public class SysUserToken implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** 用户ID */
    @Ecolumn(name = "sys_user_id")
    private Integer sysUserId;

    /** IP */
    @Ecolumn(name = "ip")
    private String ip;

    /** 令牌（唯一） */
    @Ecolumn(name = "access_token")
    private String accessToken;

    /** 用户代理 */
    @Ecolumn(name = "user_agent")
    private String userAgent;

    /** 过期时间 */
    @Ecolumn(name = "expire_time")
    private java.util.Date expireTime;

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