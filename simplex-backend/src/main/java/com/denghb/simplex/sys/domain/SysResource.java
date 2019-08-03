package com.denghb.simplex.sys.domain;

import com.denghb.eorm.annotation.Ecolumn;
import com.denghb.eorm.annotation.Etable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统资源
 *
 * @author denghb
 */
@lombok.Data()
@Etable(name = "tb_sys_resource")
public class SysResource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@Ecolumn(name = "id", primaryKey = true)
	private Integer id;
	
	@ApiModelProperty(value = "类型")
	@Ecolumn(name = "type")
	private String type;
	
	@ApiModelProperty(value = "标题")
	@Ecolumn(name = "title")
	private String title;
	
	@ApiModelProperty(value = "资源")
	@Ecolumn(name = "uri")
	private String uri;
	
	@ApiModelProperty(value = "图标")
	@Ecolumn(name = "icon")
	private String icon;
	
	@ApiModelProperty(value = "请求方法")
	@Ecolumn(name = "method")
	private String method;
	
	@ApiModelProperty(value = "父ID")
	@Ecolumn(name = "parent_id")
	private Integer parentId;
	
	@ApiModelProperty(value = "公开")
	@Ecolumn(name = "opened")
	private Integer opened;
	
	@ApiModelProperty(value = "排序,升序")
	@Ecolumn(name = "seq")
	private Integer seq;
	
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