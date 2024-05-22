package com.cyl.wms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.StockMapper;
import com.cyl.wms.domain.Stock;
import com.cyl.wms.pojo.query.StockQuery;

/**
 * 库存Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class StockService {
    @Autowired
    private StockMapper stockMapper;

    /**
     * 查询库存
     *
     * @param id 库存主键
     * @return 库存
     */
    public Stock selectById(Long id) {
        return stockMapper.selectById(id);
    }

    /**
     * 查询库存列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 库存
     */
    public List<Stock> selectList(StockQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Stock> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        Long goodsId = query.getGoodsId();
        if (goodsId != null) {
            qw.eq("goods_id", goodsId);
        }
        LocalDateTime prodDate = query.getProdDate();
        if (prodDate != null) {
            qw.eq("prod_date", prodDate);
        }
        BigDecimal maxQuantity = query.getMaxQuantity();
        if (maxQuantity != null) {
            qw.eq("max_quantity", maxQuantity);
        }
        BigDecimal aveQuantity = query.getAveQuantity();
        if (aveQuantity != null) {
            qw.eq("ave_quantity", aveQuantity);
        }
        BigDecimal minQuantity = query.getMinQuantity();
        if (minQuantity != null) {
            qw.eq("min_quantity", minQuantity);
        }
        return stockMapper.selectList(qw);
    }

    /**
     * 新增库存
     *
     * @param stock 库存
     * @return 结果
     */
    public int insert(Stock stock) {
        stock.setDelFlag(0);
        stock.setCreateTime(LocalDateTime.now());
        return stockMapper.insert(stock);
    }

    /**
     * 修改库存
     *
     * @param stock 库存
     * @return 结果
     */
    public int update(Stock stock) {
        return stockMapper.updateById(stock);
    }

    /**
     * 批量删除库存
     *
     * @param ids 需要删除的库存主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return stockMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存信息
     *
     * @param id 库存主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return stockMapper.updateDelFlagByIds(ids);
    }
}
