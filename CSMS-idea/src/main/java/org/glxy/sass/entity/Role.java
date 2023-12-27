package org.glxy.sass.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("role")
@ApiModel(value = "角色")
public class Role {

    @ApiModelProperty(value = "角色表id（自增）")
    @TableId(value = "role_id",type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty(value = "用户学号")
    private String usersStnumber;

    @ApiModelProperty(value = "用户姓名")
    private String usersName;

    @ApiModelProperty(value = "用户拥有角色")
    private String roleValue;
}
