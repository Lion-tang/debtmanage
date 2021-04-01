package com.lyontang.debtmanage.entity;

import lombok.Data;

@Data
public class Bar {
    //先用List<Bar>接数据库查到的，后端再处理赋值给BarVO
    private String registerDate;
    private Integer amount;
}
