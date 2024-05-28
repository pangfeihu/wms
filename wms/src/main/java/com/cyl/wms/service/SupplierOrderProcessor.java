package com.cyl.wms.service;
import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.domain.WmInventory;
import com.cyl.wms.domain.WmOrder;
import com.cyl.wms.domain.WmOrderDetail;
import com.cyl.wms.domain.entity.Supplier;
import com.cyl.wms.enums.OrderType;
import com.cyl.wms.enums.UserType;
import com.cyl.wms.factory.OrderProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;

@Service
public class SupplierOrderProcessor implements IOrderProcessor {

    @Autowired
    private WmInventoryService wmInventoryService;

    @Autowired
    private SupplierService supplierService;

    @Override
    public void create(WmOrder order) {
        // 查询供应商信息
        Supplier supplier = supplierService.selectById(order.getUserId());
        // 判断供应商是否存在
        if(null == supplier) {
            throw new RuntimeException("供应商信息不存在");
        }
        // 赋值订单信息
        order.setUserName(supplier.getSupplierName());
    }

    @Override
    public void complete(WmOrder order) {
        // 转换为库存信息
        List<WmInventory> wmInventories = wmInventoryService.converByOrder(order.getWarehouseId(),order.getDetails());
        // 增加库存
        wmInventoryService.placing(wmInventories);
        // 采购订单
        if(OrderType.SUPPLIER_IN_ORDER.getCode().equals(order.getOrderType())) {
            // 增加库存
            wmInventoryService.placing(wmInventories);
            return;
        }
        // 退货订单
        if(OrderType.SUPPLIER_OUT_ORDER.getCode().equals(order.getOrderType())) {
            // 减少库存
            wmInventoryService.take(wmInventories);
            return;
        }
        throw new RuntimeException("系统错误");
    }

    @Override
    public void calcAmount(WmOrderDetail orderDetail,WmGoodsUnit goodsUnit) {
        // 获取采购价格
        BigDecimal price = goodsUnit.getPurchaseUnitPrice();
        // 计算单品总金额
        BigDecimal money = price.multiply(new BigDecimal(orderDetail.getRealQuantity()));
        // 设置金额
        orderDetail.setMoney(money);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OrderProcessorFactory.register(UserType.SUPPLIER.getCode(),this);
    }

}
