package com.lyontang.debtmanage.entity.VO;

import lombok.Data;

import java.util.List;

@Data
public class DataVO<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
    //    减少设置VO重复代码
    public  static <T> DataVO<T> getDataVO(int count, List<T> list) {
        DataVO<T> dataVO = new DataVO<>();
        dataVO.setData(list);
        dataVO.setCode(0);
        dataVO.setCount(count);
        dataVO.setMsg("");
        return dataVO;
    }

}
