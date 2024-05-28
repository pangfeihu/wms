package com.cyl.wms.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.constant.OrderConstant;
import com.cyl.wms.convert.WmInventoryConvert;
import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.domain.WmOrderDetail;
import com.cyl.wms.domain.WmWarehouse;
import com.cyl.wms.enums.OrderStatus;
import com.cyl.wms.factory.OrderProcessorFactory;
import com.cyl.wms.mapper.WmGoodsUnitMapper;
import com.cyl.wms.mapper.WmInventoryMapper;
import com.cyl.wms.pojo.vo.WmOrderVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmOrderMapper;
import com.cyl.wms.domain.WmOrder;
import com.cyl.wms.pojo.query.WmOrderQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmOrderService {
    @Autowired
    private WmOrderMapper wmOrderMapper;

    @Autowired
    private WmOrderService wmOrderService;

    @Autowired
    private WmGoodsUnitService wmGoodsUnitService;

    @Autowired
    private WmGoodsUnitMapper wmGoodsUnitMapper;

    @Autowired
    private WmOrderDetailService wmOrderDetailService;

    @Autowired
    private WmInventoryConvert wmInventoryConvert;

    @Autowired
    private WmInventoryMapper wmInventoryMapper;

    @Autowired
    private WmInventoryService wmInventoryService;
    @Autowired
    private WmGoodsService wmGoodsService;
    @Autowired
    private WmWarehouseService wmWarehouseService;

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    public WmOrder selectById(Long id) {
        return wmOrderMapper.selectById(id);
    }

    /**
     * 查询订单列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单
     */
    public List<WmOrder> selectList(WmOrderQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmOrder> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Integer orderType = query.getOrderType();
        if (orderType != null) {
            qw.eq("order_type", orderType);
        }
        Long userType = query.getUserType();
        if (userType != null) {
            qw.eq("user_type", userType);
        }
        Long userNo = query.getUserNo();
        if (userNo != null) {
            qw.eq("user_no", userNo);
        }
        Long userNameLike = query.getUserNameLike();
        if (userNameLike != null) {
            qw.like("user_name", userNameLike);
        }
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        Long warehouseNameLike = query.getWarehouseNameLike();
        if (warehouseNameLike != null) {
            qw.like("warehouse_name", warehouseNameLike);
        }
        BigDecimal payableAmount = query.getPayableAmount();
        if (payableAmount != null) {
            qw.eq("payable_amount", payableAmount);
        }
        Integer orderStatus = query.getOrderStatus();
        if (orderStatus != null) {
            qw.eq("order_status", orderStatus);
        }
        return wmOrderMapper.selectList(qw);
    }

    /**
     * 新增订单
     *
     * @param wmOrder 订单
     * @return 结果
     */
    public int insert(WmOrder wmOrder) {
        // 获取订单明细
        List<WmOrderDetail> details = wmOrder.getDetails();
        // 判断订单明细是否为空
        if(CollUtil.isEmpty(details)) {
            throw new RuntimeException("订单明细为空,请添加商品");
        }
        // 查询仓库信息
        WmWarehouse wmWarehouse = wmWarehouseService.selectById(wmOrder.getWarehouseId());
        // 判断仓库是否存在
        if(null == wmWarehouse) {
            throw new RuntimeException("仓库信息不存在");
        }
        // 获取所有的商品编号
        List<Long> goodsIds = details.stream().map(WmOrderDetail::getGoodsId).collect(Collectors.toList());
        // 获取所有商品明细信息
        Map<String, WmGoodsUnit> goodsDetailMap = wmGoodsUnitService.selectMapDetail(goodsIds);
        // 订单总金额
        BigDecimal payableAmount = new BigDecimal(0);
        // 获取当前系统时间
        LocalDateTime now = LocalDateTime.now();
        // 获取订单处理器
        IOrderProcessor orderProcessor = OrderProcessorFactory.get(wmOrder.getUserType().intValue());
        // 循环所有订单信息
        for (WmOrderDetail detail : details) {
            // 判断商品信息是否存在
            String key = detail.getGoodsId() + "_" + detail.getUnitId();
            // 获取商品信息
            WmGoodsUnit goods = goodsDetailMap.get(key);
            // 设置商品金额
            orderProcessor.calcAmount(detail,goods);
            // 计算订单总金额
            payableAmount = payableAmount.add(detail.getMoney());
        }
        // 订单特殊化处理
        orderProcessor.create(wmOrder);
        // 设置订单属性
        wmOrder.setWarehouseName(wmWarehouse.getWarehouseName());
        wmOrder.setPayableAmount(payableAmount);
        wmOrder.setOrderStatus(OrderStatus.CREATE.getCode());
        wmOrder.setOrderStatusDesc(OrderStatus.CREATE.getValue());
        wmOrder.setDelFlag(0);
        wmOrder.setUpdateBy(wmOrder.getCreateBy());
        wmOrder.setCreateTime(now);
        wmOrder.setUpdateTime(now);
        // 保存到数据库当中
        int insert = wmOrderMapper.insert(wmOrder);
        // 设置附属信息
        for (WmOrderDetail detail : details) {
            // 添加订单编号
            detail.setOrderId(wmOrder.getId());
            // 设置订单创建时间
            detail.setDelFlag(0);
            detail.setCreateTime(now);
            detail.setUpdateTime(now);
        }
        // 保存订单明细
        wmOrderDetailService.saveBatch(details);
        // 返回结果
        return insert;
    }

    /**
     * 修改订单
     *
     * @param wmOrder 订单
     * @return 结果
     */
    public int update(WmOrder wmOrder) {
        return wmOrderMapper.updateById(wmOrder);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Transactional
    public int deleteByIds(Long[] ids) {
        // 转换为集合
        List<Long> orderIds = Arrays.stream(ids).collect(Collectors.toList());
        // 删除订单信息
        int result = wmOrderMapper.deleteBatchIds(orderIds);
        // 删除订单明细
        wmOrderDetailService.deleteByOrderIds(orderIds);
        // 返回结果
        return result;
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmOrderMapper.updateDelFlagByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer complete(Long[] ids, Long userId) {
        // 循环处理所有订单
        for (Long id : ids) {
            // 处理单个订单
            complete(id, userId);
        }
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer complete(Long id, Long userId) {
        // 获取订单信息
        WmOrder wmOrder = wmOrderMapper.selectById(id);
        // 判断订单是否存在
        if(null == wmOrder) {
            throw new RuntimeException("订单不存在.");
        }
        // 判断订单状态是否可用
        if(!wmOrder.getOrderStatus().equals(OrderConstant.CREATE_STATUS)) {
            throw new RuntimeException("订单不可操作.");
        }
        // 获取系统当前时间
        LocalDateTime time = LocalDateTime.now();
        // 修改订单状态
        wmOrder.setUpdateBy(userId);
        // 设置系统当前时间
        wmOrder.setUpdateTime(time);
        // 设置订单状态
        wmOrder.setOrderStatus(OrderStatus.COMPLETE.getCode());
        wmOrder.setOrderStatusDesc(OrderStatus.COMPLETE.getValue());
        // 修改订单状态
        int result = wmOrderMapper.updateById(wmOrder);
        // 获取订单明细信息
        List<WmOrderDetail> wmOrderDetails = wmOrderDetailService.selectByOrderId(wmOrder.getId());
        // 判断订单明细是否为空
        if(CollUtil.isEmpty(wmOrderDetails)) {
            throw new RuntimeException("订单明细为空,不能操作");
        }
        // 设置库存明细
        wmOrder.setDetails(wmOrderDetails);
        // 获取订单处理器
        IOrderProcessor orderProcessor = OrderProcessorFactory.get(wmOrder.getUserType().intValue());
        // 处理订单信息
        orderProcessor.complete(wmOrder);
        // 添加流水信息
        return result;
    }

}
