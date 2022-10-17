package com.skooch.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_user")
public class User {
    private Long id;
    private String name;

    @TableField(value = "pwq", select = false)
    private String password;
    private Integer age;
    private String tel;

    @TableField(exist = false)
    private Integer online;
}
