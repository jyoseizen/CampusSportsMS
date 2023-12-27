package org.glxy.sass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("site")
@ApiModel(value = "场地")
public class Site {

    @ApiModelProperty(value = "场地表id（自增）")
    @TableId(value = "site_id",type = IdType.AUTO)
    private Integer siteId;

    @ApiModelProperty(value = "场地编号")
    private String siteNumber;

    @ApiModelProperty(value = "场地位置")
    private String competitionSite;
}
