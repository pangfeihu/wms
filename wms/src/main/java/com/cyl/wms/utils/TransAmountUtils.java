package com.cyl.wms.utils;

import com.cyl.wms.enums.Transaction;

import java.math.BigDecimal;

public class TransAmountUtils {

    public static BigDecimal trans(String tranType,BigDecimal transactionAmount,BigDecimal currentBalance) {
        // 判断交易金额是否为空
        if(null == transactionAmount){
            throw new RuntimeException("交易金额为空");
        }
        // 判断交易金额是否为0
        if(transactionAmount.compareTo(BigDecimal.ZERO) == 0) {
            return currentBalance;
        }
        // 判断账户余额是否为空
        if(null == currentBalance) {
            throw new RuntimeException("账户余额为空");
        }
        // 付款交易
        if(Transaction.ENTER.getCode().equals(tranType)){
            return currentBalance.subtract(transactionAmount);
        }
        // 欠款交易
        if(Transaction.EXIT.getCode().equals(tranType)){
            return currentBalance.add(transactionAmount);
        }
        // 不支持其他类型交易
        throw new RuntimeException("交易类型错误");
    }

}
