package org.glxy.sass.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@TableName("users")
@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@ApiModel(value = "用户")
public class Users {


///^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/

//    @TableId(type = IdType.AUTO)
//    @ApiModelProperty(value = "用户id（自增）")
//    private int usersId;

    @ApiModelProperty(value = "用户姓名")
    @Excel(name = "姓名")
    @NotBlank(message = "[姓名]不能为空")
    private String usersName;

    @TableId
    @ApiModelProperty(value = "用户学号")
    @Excel(name = "学号")
    @NotBlank(message = "[学号]不能为空")
    private String usersStnumber;

    @ApiModelProperty(value = "用户性别")
    @Excel(name = "性别", replace = {"男_0", "女_1"})   //替换值
    @Pattern(regexp = "[01]", message = "性别错误")  //验证导入值
    @NotBlank(message = "[性别]不能为空")
    private int usersSex;

    @ApiModelProperty(value = "用户电话号码")
    @Excel(name = "电话")
    @NotBlank(message = "[电话]不能为空")
    private String usersPhone;

    @ApiModelProperty(value = "用户密码")
    @Excel(name = "密码")
    private String usersPwd;


    @ApiModelProperty(value = "用户学院名称")
    @Excel(name = "学院")
    @NotBlank(message = "[学院]不能为空")
    private String collegeName;

//    @ApiModelProperty(value = "用户参与项目")
//    @Excel(name = "参与项目")
//    private String competitionNb;

    @ApiModelProperty(value = "用户头像保存路径")
    private String usersUrl;

    @ApiModelProperty(value = "用户邮箱")
    private String usersEmail;


}
