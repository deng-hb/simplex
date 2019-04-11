package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;

/**
 * 访问记录
 * DDL
 * 
 <pre>
CREATE TABLE `tb_sys_access_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'IP',
  `method` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求方式',
  `url` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '路径',
  `user_agent` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户代理',
  `access_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问令牌',
  `start_time` datetime(3) NOT NULL COMMENT '开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '结束时间',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='访问记录'
 <pre>
 * @author denghb
 * @generateTime Fri Apr 12 00:55:43 CST 2019
 */
@Etable(name="tb_sys_access_log",database="simplex")
public class SysAccessLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Ecolumn(name="id", primaryKey = true)
	private Integer id;
	
	/** IP */
	@Ecolumn(name="ip")
	private String ip;
	
	/** 请求方式 */
	@Ecolumn(name="method")
	private String method;
	
	/** 路径 */
	@Ecolumn(name="url")
	private String url;
	
	/** 用户代理 */
	@Ecolumn(name="user_agent")
	private String userAgent;
	
	/** 访问令牌 */
	@Ecolumn(name="access_token")
	private String accessToken;
	
	/** 开始时间 */
	@Ecolumn(name="start_time")
	private java.util.Date startTime;
	
	/** 结束时间 */
	@Ecolumn(name="end_time")
	private java.util.Date endTime;
	
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
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
		StringBuffer str = new StringBuffer("SysAccessLog [");
		str.append("id=\"");
		str.append(id);
		str.append("\"");
		str.append(",");
		str.append("ip=\"");
		str.append(ip);
		str.append("\"");
		str.append(",");
		str.append("method=\"");
		str.append(method);
		str.append("\"");
		str.append(",");
		str.append("url=\"");
		str.append(url);
		str.append("\"");
		str.append(",");
		str.append("userAgent=\"");
		str.append(userAgent);
		str.append("\"");
		str.append(",");
		str.append("accessToken=\"");
		str.append(accessToken);
		str.append("\"");
		str.append(",");
		str.append("startTime=\"");
		str.append(startTime);
		str.append("\"");
		str.append(",");
		str.append("endTime=\"");
		str.append(endTime);
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