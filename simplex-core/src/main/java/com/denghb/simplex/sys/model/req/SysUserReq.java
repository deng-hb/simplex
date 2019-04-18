package com.denghb.simplex.sys.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SysUserReq {

    @ApiModelProperty(value = "null为创建，其他为编辑")
    private Integer id;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "姓名不能为空")
    private String name;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @NotNull(message = "生日不能为空")
    private Date birthday;

    @NotNull(message = "邮箱不能为空")
    private String email;

    @NotNull(message = "角色不能为空")
    private Integer sysRoleId;
}
