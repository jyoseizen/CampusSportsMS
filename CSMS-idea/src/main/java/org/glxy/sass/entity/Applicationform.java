package org.glxy.sass.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@TableName("competition")
@Data
@ApiModel(value = "用户报名名单")
public class Applicationform {

    @ApiModelProperty(value = "用户学号")
    private String usersStnumber;

    @ApiModelProperty(value = "比赛编号")
    private String competitionNb;
}
