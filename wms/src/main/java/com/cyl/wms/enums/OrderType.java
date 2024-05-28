package com.cyl.wms.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrderType {

    SUPPLIER_IN_ORDER(1,"采购入库订单"),

    SUPPLIER_OUT_ORDER(2,"采购退库订单"),

    CUSTOMER_OUT_ORDER(3,"客户出库订单"),

    CUSTOMER_IN_ORDER(4,"客户入库订单"),

    MOVE_WAREHOUSE(5,"采购退库订单");

    private Integer code;

    private String value;

    public static OrderType getByCode(int code) {
        switch (code) {
            case 1:
                return SUPPLIER_IN_ORDER;
            case 2:
                return SUPPLIER_OUT_ORDER;
            case 3:
                return CUSTOMER_OUT_ORDER;
            case 4:
                return CUSTOMER_IN_ORDER;
            case 5:
                return MOVE_WAREHOUSE;
            default:
                throw new RuntimeException("订单类型不存在");
        }
    }

}
