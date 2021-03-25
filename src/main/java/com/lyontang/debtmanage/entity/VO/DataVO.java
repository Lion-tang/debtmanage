package com.lyontang.debtmanage.entity.VO;

import lombok.Data;

import java.util.List;

@Data
public class DataVO<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
