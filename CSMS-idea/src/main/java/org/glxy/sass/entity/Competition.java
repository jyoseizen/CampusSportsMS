package org.glxy.sass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@TableName("competition")
@Data
@ApiModel(value = "比赛项目")
public class Competition {
//    @TableId(type = IdType.AUTO)
//    @ApiModelProperty(value = "比赛id（自增）")
//    private int competitionId;

    @TableId
    @ApiModelProperty(value = "比赛编号")
    private String competitionNb;

    @ApiModelProperty(value = "比赛名字")
    private String competitionNe;

    @ApiModelProperty(value = "比赛地点")
    private String competitionSite;

    @ApiModelProperty(value = "比赛人数")
    private int competitionPe;

    @ApiModelProperty(value = "比赛剩余报名人数")
    private int competitionTnor;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "比赛开始时间")
    private String competitionStart;

    @ApiModelProperty(value = "比赛结束时间")
    private String competitionEnd;

    @ApiModelProperty(value = "报名开始时间")
    private String applyStart;

    @ApiModelProperty(value = "报名结束时间")
    private String applyEnd;

    @TableField(exist = false)
    private List<Users> iusersList;
}
