package com.cyl.wms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrderStatus {


    CREATE(1,"订单创建"),

    COMPLETE(2,"订单完成"),

    CANCEL(3,"订单取消");

    private Integer code;

    private String value;

    public static OrderStatus getByCode(int code) {
        switch (code) {
            case 1:
                return CREATE;
            case 2:
                return COMPLETE;
            case 3:
                return CANCEL;
            default:
                throw new RuntimeException("订单类型不存在");
        }
    }

}
