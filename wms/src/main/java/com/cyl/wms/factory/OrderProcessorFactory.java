package com.cyl.wms.factory;

import com.cyl.wms.service.IOrderProcessor;

import java.util.HashMap;
import java.util.Map;

public class OrderProcessorFactory {

    public static Map<Integer, IOrderProcessor> orderProcessorMap = new HashMap<Integer, IOrderProcessor>();

    public static void register(Integer key, IOrderProcessor processor) {
        // 判断key是否为空
        if (null == key || null == processor) {
            throw new RuntimeException("系统异常");
        }
        // 注册处理器
        orderProcessorMap.put(key, processor);
    }

    public static IOrderProcessor get(Integer key) {
        // 获取处理器
        return orderProcessorMap.get(key);
    }

}
