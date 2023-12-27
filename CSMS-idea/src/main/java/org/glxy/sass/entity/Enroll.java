package org.glxy.sass.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@TableName("enroll")
@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@ApiModel(value = "报名表")
public class Enroll {

    @ApiModelProperty(value = "比赛报名列表id（自增）")
    @TableId(value = "enroll_id",type = IdType.AUTO)
    private Integer enrollId;

    @ApiModelProperty(value = "比赛编号")
    private String competitionNb;

    @ApiModelProperty(value = "比赛名字")
    private String competitionNe;

    @ApiModelProperty(value = "比赛开始时间")
    private String competitionStart;

    @ApiModelProperty(value = "比赛结束时间")
    private String competitionEnd;

    @ApiModelProperty(value = "比赛地点")
    private String competitionSite;

    @ApiModelProperty(value = "用户学号")
    private String usersStnumber;

    @ApiModelProperty(value = "用户姓名")
    private String usersName;

    @ApiModelProperty(value = "用户性别")
    private int usersSex;

    @ApiModelProperty(value = "用户电话号码")
    private String usersPhone;

    @ApiModelProperty(value = "用户学院名称")
    private String collegeName;

    @ApiModelProperty(value = "比赛成绩")
    private String competitionScore;

    @ApiModelProperty(value = "比赛积分")
    private String competitionIntegral;
}
