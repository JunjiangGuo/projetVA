package com.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Transducer {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String state;
    private String name;
    private Integer nid;
    private String content;

    @TableField(exist = false)//非数据库的字段
    private Nodes nodes;

}
