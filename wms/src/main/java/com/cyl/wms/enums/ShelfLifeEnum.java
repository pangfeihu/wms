package com.cyl.wms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ShelfLifeEnum {

    DAY(1, ChronoUnit.DAYS),

    MONTH(2,ChronoUnit.MONTHS),

    YEAR(3,ChronoUnit.YEARS);

    private Integer code;

    private ChronoUnit value;

    public static ShelfLifeEnum getByCode(int code) {
        switch (code) {
            case 1:
                return DAY;
            case 2:
                return MONTH;
            case 3:
                return YEAR;
            default:
                throw new RuntimeException("订单类型不存在");
        }
    }
}
