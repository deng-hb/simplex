package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统资源
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_resource")
public class SysResource implements java.io.Serializable {

    /** ID */
    @Ecolumn(name = "id", primaryKey = true)
    private Integer id;

    /** 类型 */
    @Ecolumn(name = "type")
    private String type;

    /** 标题 */
    @Ecolumn(name = "title")
    private String title;

    /**  */
    @Ecolumn(name = "method")
    private String method;

    /** 路径 */
    @Ecolumn(name = "uri")
    private String uri;

    /** 图标 */
    @Ecolumn(name = "icon")
    private String icon;

    /** 父ID */
    @Ecolumn(name = "parent_id")
    private Integer parentId;

    /** 公开 */
    @Ecolumn(name = "opened")
    private Integer opened;

    /** 排序,升序 */
    @Ecolumn(name = "seq")
    private Integer seq;

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