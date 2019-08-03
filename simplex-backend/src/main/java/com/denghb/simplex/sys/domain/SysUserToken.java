package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户令牌
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_user_token")
public class SysUserToken implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@Ecolumn(name = "id", primaryKey = true)
	private Integer id;
	
	@ApiModelProperty(value = "用户ID")
	@Ecolumn(name = "sys_user_id")
	private Integer sysUserId;
	
	@ApiModelProperty(value = "IP")
	@Ecolumn(name = "ip")
	private String ip;
	
	@ApiModelProperty(value = "令牌（唯一）")
	@Ecolumn(name = "access_token")
	private String accessToken;
	
	@ApiModelProperty(value = "用户代理")
	@Ecolumn(name = "user_agent")
	private String userAgent;
	
	@ApiModelProperty(value = "过期时间")
	@Ecolumn(name = "expire_time")
	private java.util.Date expireTime;
	
	@ApiModelProperty(value = "插入时间")
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