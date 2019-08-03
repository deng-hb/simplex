package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_user")
public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@Ecolumn(name = "id", primaryKey = true)
	private Integer id;
	
	@ApiModelProperty(value = "账号")
	@Ecolumn(name = "username")
	private String username;
	
	@ApiModelProperty(value = "姓名")
	@Ecolumn(name = "name")
	private String name;
	
	@ApiModelProperty(value = "生日")
	@Ecolumn(name = "birthday")
	private java.util.Date birthday;
	
	@ApiModelProperty(value = "邮箱")
	@Ecolumn(name = "email")
	private String email;
	
	@ApiModelProperty(value = "状态1正常，2:冻结")
	@Ecolumn(name = "status")
	private Integer status;
	
	@ApiModelProperty(value = "登录错误次数")
	@Ecolumn(name = "sign_error_size")
	private Integer signErrorSize;
	
	@ApiModelProperty(value = "登录错误上限")
	@Ecolumn(name = "sign_error_limit")
	private Integer signErrorLimit;
	
	@ApiModelProperty(value = "角色ID")
	@Ecolumn(name = "sys_role_id")
	private Integer sysRoleId;
	
	@ApiModelProperty(value = "操作人")
	@Ecolumn(name = "operator")
	private Integer operator;
	
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