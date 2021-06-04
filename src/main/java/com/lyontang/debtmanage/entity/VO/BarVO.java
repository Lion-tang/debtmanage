package com.lyontang.debtmanage.entity.VO;

import com.lyontang.debtmanage.entity.Bar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@NoArgsConstructor
public class BarVO {
    private List<String> registerDate;
    private List<Integer> amount;

    //    减少重复设置VO代码
    public static BarVO getBarVO(List list) {
        BarVO barVO = new BarVO();
        List<String> registerDates = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Bar bar = (Bar) iterator.next();
            registerDates.add(bar.getRegisterDate());
            amounts.add(bar.getAmount());
        }
        barVO.setRegisterDate(registerDates);
        barVO.setAmount(amounts);
        return barVO;
    }
}
