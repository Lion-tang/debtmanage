package com.lyontang.debtmanage.entity;

import com.lyontang.debtmanage.utils.Md5Utils;
import lombok.Data;

@Data
public class User {
    private Integer uId;
    private String userName;
    private String password;
    private Integer rId;

}
