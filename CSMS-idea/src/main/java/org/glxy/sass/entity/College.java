package org.glxy.sass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("college")
@Data
@ApiModel(value = "学院")
public class College {

    @TableId
    @ApiModelProperty(value = "学院编号")
    private String collegeNumber;

    @ApiModelProperty(value = "学院名称")
    private String collegeName;

}
