package com.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Nodes {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String actions;
    private String content;
    private Integer rid;

    @TableField(exist = false)//非数据库的字段
    private Region region;

}
