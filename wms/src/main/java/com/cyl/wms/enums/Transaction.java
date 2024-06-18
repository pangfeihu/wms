package com.cyl.wms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Transaction {


    ENTER("11", "结款"),

    EXIT("22","应付");

    private String code;

    private String value;

    public static String getByCode(int code) {
        switch (code) {
            case 11:
                return ENTER.value;
            case 22:
                return EXIT.value;
            default:
                throw new RuntimeException("交易类型不存在");
        }
    }

}
