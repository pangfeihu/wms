package com.cyl.wms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserType {

    SUPPLIER(1,"供应商"),


    CUSTOMER(2,"客户"),


    WAREHOUSE(3,"仓库");

    private Integer code;

    private String value;

    public static UserType getByCode(int code) {
        switch (code) {
            case 1:
                return SUPPLIER;
            case 2:
                return CUSTOMER;
            case 3:
                return WAREHOUSE;
            default:
                throw new RuntimeException("订单类型不存在");
        }
    }

}
