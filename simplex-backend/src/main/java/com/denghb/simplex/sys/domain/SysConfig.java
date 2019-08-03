package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_config")
public class SysConfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@Ecolumn(name = "id", primaryKey = true)
	private Integer id;
	
	@ApiModelProperty(value = "配置代码")
	@Ecolumn(name = "code")
	private String code;
	
	@ApiModelProperty(value = "配置值")
	@Ecolumn(name = "value")
	private String value;
	
	@ApiModelProperty(value = "配置描述")
	@Ecolumn(name = "description")
	private String description;
	
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