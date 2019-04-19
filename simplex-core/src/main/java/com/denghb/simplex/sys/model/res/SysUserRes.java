package com.denghb.simplex.sys.model.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 15:04
 */
@Data
public class SysUserRes extends SysBaseRes {

    private String username;

    private String name;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    private String email;

    private Integer sysRoleId;

    private Integer status;

    private String statusName;

    @ApiModelProperty("操作人姓名")
    private String operatorName;
}
