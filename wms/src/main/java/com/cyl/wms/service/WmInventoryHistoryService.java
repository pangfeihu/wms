package com.cyl.wms.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmInventoryHistoryMapper;
import com.cyl.wms.domain.WmInventoryHistory;
import com.cyl.wms.pojo.query.WmInventoryHistoryQuery;

/**
 * 库存记录Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmInventoryHistoryService {
    @Autowired
    private WmInventoryHistoryMapper wmInventoryHistoryMapper;

    /**
     * 查询库存记录
     *
     * @param id 库存记录主键
     * @return 库存记录
     */
    public WmInventoryHistory selectById(Long id) {
        return wmInventoryHistoryMapper.selectById(id);
    }

    /**
     * 查询库存记录列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 库存记录
     */
    public List<WmInventoryHistory> selectList(WmInventoryHistoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmInventoryHistory> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        Integer orderType = query.getOrderType();
        if (orderType != null) {
            qw.eq("order_type", orderType);
        }
        Long unitId = query.getUnitId();
        if (unitId != null) {
            qw.eq("unit_id", unitId);
        }
        Long goodsId = query.getGoodsId();
        if (goodsId != null) {
            qw.eq("goods_id", goodsId);
        }
        BigDecimal quantity = query.getQuantity();
        if (quantity != null) {
            qw.eq("quantity", quantity);
        }
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        return wmInventoryHistoryMapper.selectList(qw);
    }

    /**
     * 新增库存记录
     *
     * @param wmInventoryHistory 库存记录
     * @return 结果
     */
    public int insert(WmInventoryHistory wmInventoryHistory) {
        wmInventoryHistory.setDelFlag(0);
        wmInventoryHistory.setCreateTime(LocalDateTime.now());
        return wmInventoryHistoryMapper.insert(wmInventoryHistory);
    }

    /**
     * 修改库存记录
     *
     * @param wmInventoryHistory 库存记录
     * @return 结果
     */
    public int update(WmInventoryHistory wmInventoryHistory) {
        return wmInventoryHistoryMapper.updateById(wmInventoryHistory);
    }

    /**
     * 批量删除库存记录
     *
     * @param ids 需要删除的库存记录主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmInventoryHistoryMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存记录信息
     *
     * @param id 库存记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmInventoryHistoryMapper.updateDelFlagByIds(ids);
    }
}
