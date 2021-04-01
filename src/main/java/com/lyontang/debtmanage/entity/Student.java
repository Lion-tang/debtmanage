package com.lyontang.debtmanage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer sId;
    private String studentName;
    private String sex;
    private String address;
    private String idCard;
    private String phone;
    private Integer amount;
    private String userName;
    private String rId;
    private String wantDelete;
    private String registerDate;
}
