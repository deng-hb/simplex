package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统接口
 * DDL
 * 
 <pre>
CREATE TABLE `tb_sys_api` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `method` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'GET' COMMENT '请求方法',
  `uri` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '路径（唯一）',
  `description` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '描述',
  `opened` tinyint(2) NOT NULL DEFAULT '0' COMMENT '未登录访问（1:开,0:关）',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uri` (`uri`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统接口'
 <pre>
 * @author denghb
 * @generateTime Fri Apr 12 00:55:43 CST 2019
 */
@Etable(name="tb_sys_api",database="simplex")
public class SysApi implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Ecolumn(name="id", primaryKey = true)
	private Integer id;
	
	/** 请求方法 */
	@Ecolumn(name="method")
	private String method;
	
	/** 路径（唯一） */
	@Ecolumn(name="uri")
	private String uri;
	
	/** 描述 */
	@Ecolumn(name="description")
	private String description;
	
	/** 未登录访问（1:开,0:关） */
	@Ecolumn(name="opened")
	private Integer opened;
	
	/** 操作人 */
	@Ecolumn(name="operator")
	private Integer operator;
	
	/** 创建时间 */
	@Ecolumn(name="created_time")
	private java.util.Date createdTime;
	
	/** 更新时间 */
	@Ecolumn(name="updated_time")
	private java.util.Date updatedTime;
	
	/** 逻辑删除 */
	@Ecolumn(name="deleted")
	private Integer deleted;
	
	/** 版本号 */
	@Ecolumn(name="version")
	private Integer version;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOpened() {
		return opened;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public java.util.Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = createdTime;
	}

	public java.util.Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(java.util.Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("SysApi [");
		str.append("id=\"");
		str.append(id);
		str.append("\"");
		str.append(",");
		str.append("method=\"");
		str.append(method);
		str.append("\"");
		str.append(",");
		str.append("uri=\"");
		str.append(uri);
		str.append("\"");
		str.append(",");
		str.append("description=\"");
		str.append(description);
		str.append("\"");
		str.append(",");
		str.append("opened=\"");
		str.append(opened);
		str.append("\"");
		str.append(",");
		str.append("operator=\"");
		str.append(operator);
		str.append("\"");
		str.append(",");
		str.append("createdTime=\"");
		str.append(createdTime);
		str.append("\"");
		str.append(",");
		str.append("updatedTime=\"");
		str.append(updatedTime);
		str.append("\"");
		str.append(",");
		str.append("deleted=\"");
		str.append(deleted);
		str.append("\"");
		str.append(",");
		str.append("version=\"");
		str.append(version);
		str.append("\"");
		
		str.append("]");
	
		return str.toString();
	}
}