package com.lyontang.debtmanage.entity;

import lombok.Data;


import java.util.Date;

@Data
public class StudentInfo {
    private String studentName;
    private String idCard;
    private String sex;
    private String address;
    private String phone;
    private Integer amount;
    private String userName;
    private String registerDate;//这里采用字符串是由于数据库能自动转换为对应字符串(YYYY-MM-dd)
    // Date类型数据库转换后是YYYY-MM-dd HH-mm-ss类型，不便于统计分析。
    private  String deleteDate;
}
