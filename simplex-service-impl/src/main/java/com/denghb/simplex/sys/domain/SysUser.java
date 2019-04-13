package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 系统用户
 * DDL
 * 
 <pre>
CREATE TABLE `tb_sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `status` int(11) NOT NULL COMMENT '状态1正常，2:冻结',
  `sign_error_size` int(11) NOT NULL DEFAULT '0' COMMENT '登录错误次数',
  `sign_error_limit` int(11) NOT NULL DEFAULT '5' COMMENT '登录错误上线',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户'
 <pre>
 * @author denghb
 * @generateTime Sat Apr 13 17:03:21 CST 2019
 */
@Etable(name="tb_sys_user",database="simplex")
public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Ecolumn(name="id", primaryKey = true)
	private Integer id;
	
	/** 账号 */
	@Ecolumn(name="username")
	private String username;
	
	/** 姓名 */
	@Ecolumn(name="name")
	private String name;
	
	/** 状态1正常，2:冻结 */
	@Ecolumn(name="status")
	private Integer status;
	
	/** 登录错误次数 */
	@Ecolumn(name="sign_error_size")
	private Integer signErrorSize;
	
	/** 登录错误上线 */
	@Ecolumn(name="sign_error_limit")
	private Integer signErrorLimit;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSignErrorSize() {
		return signErrorSize;
	}

	public void setSignErrorSize(Integer signErrorSize) {
		this.signErrorSize = signErrorSize;
	}

	public Integer getSignErrorLimit() {
		return signErrorLimit;
	}

	public void setSignErrorLimit(Integer signErrorLimit) {
		this.signErrorLimit = signErrorLimit;
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
		StringBuffer str = new StringBuffer("SysUser [");
		str.append("id=\"");
		str.append(id);
		str.append("\"");
		str.append(",");
		str.append("username=\"");
		str.append(username);
		str.append("\"");
		str.append(",");
		str.append("name=\"");
		str.append(name);
		str.append("\"");
		str.append(",");
		str.append("status=\"");
		str.append(status);
		str.append("\"");
		str.append(",");
		str.append("signErrorSize=\"");
		str.append(signErrorSize);
		str.append("\"");
		str.append(",");
		str.append("signErrorLimit=\"");
		str.append(signErrorLimit);
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