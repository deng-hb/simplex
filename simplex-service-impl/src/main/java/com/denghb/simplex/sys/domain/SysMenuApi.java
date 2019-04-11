package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统菜单接口
 * DDL
 * 
 <pre>
CREATE TABLE `tb_sys_menu_api` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `sys_api_id` int(11) DEFAULT NULL COMMENT '接口ID',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统菜单接口'
 <pre>
 * @author denghb
 * @generateTime Fri Apr 12 00:55:43 CST 2019
 */
@Etable(name="tb_sys_menu_api",database="simplex")
public class SysMenuApi implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Ecolumn(name="id", primaryKey = true)
	private Integer id;
	
	/** 菜单ID */
	@Ecolumn(name="sys_menu_id")
	private Integer sysMenuId;
	
	/** 接口ID */
	@Ecolumn(name="sys_api_id")
	private Integer sysApiId;
	
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

	public Integer getSysMenuId() {
		return sysMenuId;
	}

	public void setSysMenuId(Integer sysMenuId) {
		this.sysMenuId = sysMenuId;
	}

	public Integer getSysApiId() {
		return sysApiId;
	}

	public void setSysApiId(Integer sysApiId) {
		this.sysApiId = sysApiId;
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
		StringBuffer str = new StringBuffer("SysMenuApi [");
		str.append("id=\"");
		str.append(id);
		str.append("\"");
		str.append(",");
		str.append("sysMenuId=\"");
		str.append(sysMenuId);
		str.append("\"");
		str.append(",");
		str.append("sysApiId=\"");
		str.append(sysApiId);
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