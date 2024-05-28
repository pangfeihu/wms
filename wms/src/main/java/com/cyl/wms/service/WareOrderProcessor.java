package com.cyl.wms.service;

import com.cyl.wms.domain.*;
import com.cyl.wms.enums.OrderType;
import com.cyl.wms.enums.UserType;
import com.cyl.wms.factory.OrderProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WareOrderProcessor implements IOrderProcessor {

    @Autowired
    private WmInventoryService wmInventoryService;

    @Autowired
    private WmWarehouseService wmWarehouseService;

    @Override
    public void create(WmOrder order) {
        // 查询仓库信息
        WmWarehouse wmWarehouse = wmWarehouseService.selectById(order.getUserId());
        // 判断仓库是否存在
        if (wmWarehouse == null) {
            throw new RuntimeException("仓库信息不存在");
        }
    }

    @Override
    public void complete(WmOrder order) {
        // 转换为库存信息
        List<WmInventory> wmInventories = wmInventoryService.converByOrder(order.getWarehouseId(),order.getDetails());
        // 判断是否为移库订单
        if(!OrderType.MOVE_WAREHOUSE.getCode().equals(order.getOrderType())) {
            throw new RuntimeException("系统错误");
        }
        // 减少库存
        wmInventoryService.take(wmInventories);
        // 更换仓库编号
        wmInventories.forEach(line -> line.setWarehouseId(order.getUserId()));
        // 增加库存
        wmInventoryService.placing(wmInventories);
    }

    @Override
    public void calcAmount(WmOrderDetail orderDetail, WmGoodsUnit goodsUnit) {
        // 获取采购价格
        BigDecimal price = goodsUnit.getPurchaseUnitPrice();
        // 计算单品总金额
        BigDecimal money = price.multiply(new BigDecimal(orderDetail.getRealQuantity()));
        // 设置金额
        orderDetail.setMoney(money);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OrderProcessorFactory.register(UserType.WAREHOUSE.getCode(),this);
    }
}
