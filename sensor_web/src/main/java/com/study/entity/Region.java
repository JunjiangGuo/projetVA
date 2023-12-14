package com.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Region {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

}
