package com.lyontang.debtmanage.entity;

import lombok.Data;

@Data
public class RespEntity {
    private String msg;
    private int code;

    public static RespEntity success(String msg) {
        RespEntity respEntity = new RespEntity();
        respEntity.setMsg(msg);
        respEntity.setCode(0);
        return respEntity;
    }

    public static RespEntity fail(String msg) {
        RespEntity respEntity = new RespEntity();
        respEntity.setMsg(msg);
        respEntity.setCode(-1);
        return respEntity;
    }
}
