package com.cyl.wms.service;

import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.domain.WmInventory;
import com.cyl.wms.domain.WmOrder;
import com.cyl.wms.domain.WmOrderDetail;
import com.cyl.wms.domain.entity.Customer;
import com.cyl.wms.domain.entity.CustomerTransaction;
import com.cyl.wms.enums.OrderType;
import com.cyl.wms.enums.Transaction;
import com.cyl.wms.enums.UserType;
import com.cyl.wms.factory.OrderProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerOrderProcessor implements IOrderProcessor {

    @Autowired
    private WmInventoryService wmInventoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerTransactionService customerTransactionService;

    @Override
    public void create(WmOrder order) {
        // 查询客户信息
        Customer customer = customerService.selectById(order.getUserId());
        // 判断客户信息是否存在
        if(null == customer) {
            throw new RuntimeException("客户信息不存在");
        }
        // 设置用户名称
        order.setUserName(customer.getCustomerName());
    }

    @Override
    public void complete(WmOrder order) {
        // 转换为库存信息
        List<WmInventory> wmInventories = wmInventoryService.converByOrder(order.getWarehouseId(),order.getDetails());
        // 构建订单信息
        CustomerTransaction accountOrder = customerTransactionService.builder(order.getId(), order.getUserId(), order.getPayableAmount(), null, null);
        // 采购订单
        if(OrderType.CUSTOMER_IN_ORDER.getCode().equals(order.getOrderType())) {
            // 增加库存
            wmInventoryService.placing(wmInventories);
            // 设置附言
            accountOrder.setRemark(String.format("客户订货订单:%s,订货款.",order.getId()));
            // 设置为应付
            accountOrder.setTransactionType(Transaction.EXIT.getCode());
            // 记录资金流水
            customerTransactionService.insert(accountOrder);
            return;
        }
        // 退货订单
        if(OrderType.CUSTOMER_OUT_ORDER.getCode().equals(order.getOrderType())) {
            // 减少库存
            wmInventoryService.take(wmInventories);
            // 设置附言
            accountOrder.setRemark(String.format("客户退款订单:%s,退货款.",order.getId()));
            // 设置为应收
            accountOrder.setTransactionType(Transaction.ENTER.getCode());
            // 记录资金流水
            customerTransactionService.insert(accountOrder);
            return;
        }
        throw new RuntimeException("系统错误");
    }

    @Override
    public void calcAmount(WmOrderDetail orderDetail, WmGoodsUnit goodsUnit) {
        // 获取销售价格
        BigDecimal price = goodsUnit.getSaleUnitPrice();
        // 计算单品总金额
        BigDecimal money = price.multiply(new BigDecimal(orderDetail.getRealQuantity()));
        // 设置金额
        orderDetail.setMoney(money);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OrderProcessorFactory.register(UserType.CUSTOMER.getCode(),this);
    }
}
