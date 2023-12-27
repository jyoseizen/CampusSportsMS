package org.glxy.sass.entity.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("UsersNum")
@Data
public class UsersNum {

    private String num;

    private String boy;

    private String girl;

}
