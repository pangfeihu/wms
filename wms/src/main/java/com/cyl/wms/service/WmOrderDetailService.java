package com.cyl.wms.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmOrderDetailMapper;
import com.cyl.wms.domain.WmOrderDetail;
import com.cyl.wms.pojo.query.WmOrderDetailQuery;

/**
 * 订单明细Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmOrderDetailService extends ServiceImpl<WmOrderDetailMapper, WmOrderDetail> {
    @Autowired
    private WmOrderDetailMapper wmOrderDetailMapper;

    /**
     * 查询订单明细
     *
     * @param id 订单明细主键
     * @return 订单明细
     */
    public WmOrderDetail selectById(Long id) {
        return wmOrderDetailMapper.selectById(id);
    }

    /**
     * 查询订单明细列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单明细
     */
    public List<WmOrderDetail> selectList(WmOrderDetailQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmOrderDetail> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        Long goodsId = query.getGoodsId();
        if (goodsId != null) {
            qw.eq("goods_id", goodsId);
        }
        BigDecimal planQuantity = query.getPlanQuantity();
        if (planQuantity != null) {
            qw.eq("plan_quantity", planQuantity);
        }
        BigDecimal realQuantity = query.getRealQuantity();
        if (realQuantity != null) {
            qw.eq("real_quantity", realQuantity);
        }
        Long unit = query.getUnit();
        if (unit != null) {
            qw.eq("unit", unit);
        }
        Long type = query.getType();
        if (type != null) {
            qw.eq("type", type);
        }
        BigDecimal money = query.getMoney();
        if (money != null) {
            qw.eq("money", money);
        }
        return wmOrderDetailMapper.selectList(qw);
    }

    /**
     * 新增订单明细
     *
     * @param wmOrderDetail 订单明细
     * @return 结果
     */
    public int insert(WmOrderDetail wmOrderDetail) {
        wmOrderDetail.setDelFlag(0);
        wmOrderDetail.setCreateTime(LocalDateTime.now());
        return wmOrderDetailMapper.insert(wmOrderDetail);
    }

    /**
     * 修改订单明细
     *
     * @param wmOrderDetail 订单明细
     * @return 结果
     */
    public int update(WmOrderDetail wmOrderDetail) {
        return wmOrderDetailMapper.updateById(wmOrderDetail);
    }

    /**
     * 批量删除订单明细
     *
     * @param ids 需要删除的订单明细主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmOrderDetailMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除订单明细信息
     *
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmOrderDetailMapper.updateDelFlagByIds(ids);
    }

    public List<WmOrderDetail> selectByOrderId(Long orderId) {
        // 判断订单编号是否为空
        if(null == orderId) {
            throw new RuntimeException("订单编号为空,请检查");
        }
        QueryWrapper<WmOrderDetail> qw = new QueryWrapper<>();
        qw.eq("order_id", orderId);
        qw.eq("del_flag",0);
        return wmOrderDetailMapper.selectList(qw);
    }

    public void deleteByOrderIds(List<Long> orderIds) {
        // 判断订单编号是否为空
        if(null == orderIds) {
            throw new RuntimeException("订单编号为空,请检查");
        }
        QueryWrapper<WmOrderDetail> qw = new QueryWrapper<>();
        qw.in("order_id", orderIds);
        wmOrderDetailMapper.delete(qw);
    }
}
