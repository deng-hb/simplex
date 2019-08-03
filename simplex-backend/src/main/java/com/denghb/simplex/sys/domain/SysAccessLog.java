package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 访问记录
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_access_log")
public class SysAccessLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@Ecolumn(name = "id", primaryKey = true)
	private Integer id;
	
	@ApiModelProperty(value = "IP")
	@Ecolumn(name = "ip")
	private String ip;
	
	@ApiModelProperty(value = "请求方式")
	@Ecolumn(name = "method")
	private String method;
	
	@ApiModelProperty(value = "路径")
	@Ecolumn(name = "url")
	private String url;
	
	@ApiModelProperty(value = "用户代理")
	@Ecolumn(name = "user_agent")
	private String userAgent;
	
	@ApiModelProperty(value = "访问令牌")
	@Ecolumn(name = "access_token")
	private String accessToken;
	
	@ApiModelProperty(value = "开始时间")
	@Ecolumn(name = "start_time")
	private java.util.Date startTime;
	
	@ApiModelProperty(value = "结束时间")
	@Ecolumn(name = "end_time")
	private java.util.Date endTime;
	
	@ApiModelProperty(value = "操作人")
	@Ecolumn(name = "operator")
	private Integer operator;
	
	@ApiModelProperty(value = "创建时间")
	@Ecolumn(name = "created_time")
	private java.util.Date createdTime;
	
	@ApiModelProperty(value = "更新时间")
	@Ecolumn(name = "updated_time")
	private java.util.Date updatedTime;
	
	@ApiModelProperty(value = "逻辑删除")
	@Ecolumn(name = "deleted")
	private Integer deleted;
	
	@ApiModelProperty(value = "版本号")
	@Ecolumn(name = "version")
	private Integer version;
	

}