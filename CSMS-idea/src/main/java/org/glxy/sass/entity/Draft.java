package org.glxy.sass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@TableName("draft")
@Data
@ApiModel(value = "加油稿")
public class Draft {


    @TableId(value = "draft_id",type = IdType.AUTO)
    @ApiModelProperty(value = "加油稿id（自增）")
    private Integer draftId;

    @ApiModelProperty(value = "用户学号")
    private String usersStnumber;

    @ApiModelProperty(value = "用户姓名")
    private String usersName;

    @ApiModelProperty(value = "用户学院名称")
    private String collegeName;

    @ApiModelProperty(value = "加油稿状态（0：未通过审核；1：通过审核）")
    private String draftState;

    @ApiModelProperty(value = "加油稿状态内容")
    private String draftContent;

    @TableField(exist = false)
    private List<Users> usersList;


}
