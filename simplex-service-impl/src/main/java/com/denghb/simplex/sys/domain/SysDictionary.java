package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统字典
 * DDL
 * 
 <pre>
CREATE TABLE `tb_sys_dictionary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '代码（唯一）',
  `title` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签',
  `parent_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '父代码',
  `req` int(11) NOT NULL DEFAULT '0',
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置描述',
  `operator` int(11) DEFAULT NULL COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统字典'
 <pre>
 * @author denghb
 * @generateTime Fri Apr 12 00:55:43 CST 2019
 */
@Etable(name="tb_sys_dictionary",database="simplex")
public class SysDictionary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Ecolumn(name="id", primaryKey = true)
	private Integer id;
	
	/** 代码（唯一） */
	@Ecolumn(name="code")
	private String code;
	
	/** 标签 */
	@Ecolumn(name="title")
	private String title;
	
	/** 父代码 */
	@Ecolumn(name="parent_code")
	private String parentCode;
	
	/**  */
	@Ecolumn(name="req")
	private Integer req;
	
	/** 配置描述 */
	@Ecolumn(name="description")
	private String description;
	
	/** 操作人 */
	@Ecolumn(name="operator")
	private Integer operator;
	
	/** 插入时间 */
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getReq() {
		return req;
	}

	public void setReq(Integer req) {
		this.req = req;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		StringBuffer str = new StringBuffer("SysDictionary [");
		str.append("id=\"");
		str.append(id);
		str.append("\"");
		str.append(",");
		str.append("code=\"");
		str.append(code);
		str.append("\"");
		str.append(",");
		str.append("title=\"");
		str.append(title);
		str.append("\"");
		str.append(",");
		str.append("parentCode=\"");
		str.append(parentCode);
		str.append("\"");
		str.append(",");
		str.append("req=\"");
		str.append(req);
		str.append("\"");
		str.append(",");
		str.append("description=\"");
		str.append(description);
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