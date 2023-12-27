package org.glxy.sass.entity.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("UsersSort")
@Data
public class UsersSort {

    private String usersSex;

    private Integer sum;

//    private String type;
}
