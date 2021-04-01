package com.lyontang.debtmanage.entity.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BarVO {
    private List<String> registerDate;
    private List<Integer> amount;
}
