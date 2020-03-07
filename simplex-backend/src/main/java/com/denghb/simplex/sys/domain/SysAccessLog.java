package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 访问记录
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_access_log")
public class SysAccessLog implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** IP */
    @Ecolumn(name = "ip")
    private String ip;

    /** 请求方式 */
    @Ecolumn(name = "method")
    private String method;

    /** 路径 */
    @Ecolumn(name = "url")
    private String url;

    /** 用户代理 */
    @Ecolumn(name = "user_agent")
    private String userAgent;

    /** 访问令牌 */
    @Ecolumn(name = "access_token")
    private String accessToken;

    /** 开始时间 */
    @Ecolumn(name = "start_time")
    private java.util.Date startTime;

    /** 结束时间 */
    @Ecolumn(name = "end_time")
    private java.util.Date endTime;

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