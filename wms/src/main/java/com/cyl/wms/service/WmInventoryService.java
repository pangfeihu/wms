package com.cyl.wms.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.domain.*;
import com.cyl.wms.mapper.WmGoodsMapper;
import com.cyl.wms.pojo.vo.WmGoodsVO;
import com.cyl.wms.utils.LocalDateUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmInventoryMapper;
import com.cyl.wms.pojo.query.WmInventoryQuery;

/**
 * 库存Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmInventoryService {

    @Autowired
    private WmInventoryMapper wmInventoryMapper;

    @Autowired
    private WmGoodsService wmGoodsService;

    @Autowired
    private WmUnitService wmUnitService;

    @Autowired
    private WmGoodsUnitService  wmGoodsUnitService;


    /**
     * 查询库存
     *
     * @param id 库存主键
     * @return 库存
     */
    public WmInventory selectById(Long id) {
        // 获取库存明细
        WmInventory wmInventory = wmInventoryMapper.selectById(id);
        // 判断明细是否存在
        addDetail(wmInventory);
        // 返回数据
        return wmInventory;
    }


    public void addDetail(WmInventory wmInventory) {
        // 判断库存明细是否存在
        if(null == wmInventory) {
            return ;
        }
        // 查询商品信息
        WmGoods wmGoods = wmGoodsService.selectById(wmInventory.getGoodsId());
        // 判断商品是否存在
        if(null != wmGoods) {
            // 添加商品名称
            wmInventory.setGoodsName(wmGoods.getGoodsName());
        }
        // 获取单位信息
        WmUnit wmUnit = wmUnitService.selectById(wmInventory.getUnitId());
        // 判断是否存在
        if(null != wmUnit) {
            // 添加单位信息
            wmInventory.setUnitName(wmUnit.getUnitName());
        }
        // 获取商品明细信息
        WmGoodsUnit wmGoodsUnit = wmGoodsUnitService.selectByUnitId(wmInventory.getGoodsId(),wmInventory.getUnitId());
        // 判断商品明细信息是否存在
        if(null != wmGoodsUnit) {
            // 添加商品规格信息
            wmInventory.setSpecName(wmGoodsUnit.getSpecName());
            wmInventory.setSaleUnitPrice(wmGoodsUnit.getSaleUnitPrice());
            wmInventory.setPurchaseUnitPrice(wmInventory.getPurchaseUnitPrice());
            wmInventory.setPromoUnitPrice(wmInventory.getPurchaseUnitPrice());
        }
    }

    /**
     * 查询库存列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 库存
     */
    public List<WmInventory> selectList(WmInventoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmInventory> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long goodsId = query.getGoodsId();
        if (goodsId != null) {
            qw.eq("goods_id", goodsId);
        }
        Long unitId = query.getUnitId();
        if (unitId != null) {
            qw.eq("unit_id", unitId);
        }
        BigDecimal quantity = query.getQuantity();
        if (quantity != null) {
            qw.eq("quantity", quantity);
        }
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        Long areaId = query.getAreaId();
        if (areaId != null) {
            qw.eq("area_id", areaId);
        }
        // 获取库存信息
        List<WmInventory> wmInventories = wmInventoryMapper.selectList(qw);
        // 添加库存属性信息
        addDetail(wmInventories);
        // 返回数据
        return wmInventories;
    }

    public void addDetail(List<WmInventory> wmInventories) {
        // 判断库存是否存在
        if(CollUtil.isEmpty(wmInventories)) {
            return;
        }
        // 循环添加库存属性
        wmInventories.forEach(this::addDetail);
    }

    /**
     * 新增库存
     *
     * @param wmInventory 库存
     * @return 结果
     */
    public int insert(WmInventory wmInventory) {
        wmInventory.setDelFlag(0);
        wmInventory.setCreateTime(LocalDateTime.now());
        return wmInventoryMapper.insert(wmInventory);
    }

    /**
     * 修改库存
     *
     * @param wmInventory 库存
     * @return 结果
     */
    public int update(WmInventory wmInventory) {
        return wmInventoryMapper.updateById(wmInventory);
    }

    /**
     * 批量删除库存
     *
     * @param ids 需要删除的库存主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmInventoryMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存信息
     *
     * @param id 库存主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmInventoryMapper.updateDelFlagByIds(ids);
    }

    public List<WmInventory> converByOrder(Long warehouseId,List<WmOrderDetail> wmOrderDetails) {
        // 获取所有游戏编号
        List<Long> goodsIds = wmOrderDetails.stream()
                .map(WmOrderDetail::getGoodsId)
                .collect(Collectors.toList());
        // 获取所有商品明细信息
        Map<String, WmGoodsVO> goodsDetailMap = wmGoodsService.selectMapDetail(goodsIds);
        // 判断是否存在
        if(CollUtil.isEmpty(wmOrderDetails)) {
            return new ArrayList<>();
        }
        // 转换库存信息
        return wmOrderDetails.stream()
                .map(line -> converByOrder(warehouseId,goodsDetailMap.get(line.getGoodsId() + "_" +line.getUnitId()),line))
                .collect(Collectors.toList());
    }

    public WmInventory converByOrder(Long warehouseId,WmGoodsVO goods,WmOrderDetail wmOrderDetail) {
        // 保质期
        Integer shelfLife = goods.getShelfLife().intValue();
        // 保质期单位
        Integer shelfLifeUnit = Integer.valueOf(goods.getShelfLifeUnit());
        WmInventory wmInventory = new WmInventory();
        wmInventory.setGoodsId(wmOrderDetail.getGoodsId());
        wmInventory.setUnitId(wmOrderDetail.getUnitId());
        wmInventory.setQuantity(wmOrderDetail.getRealQuantity());
        wmInventory.setWarehouseId(warehouseId);
        wmInventory.setDelFlag(0);
        wmInventory.setCreateBy(wmOrderDetail.getCreateBy());
        wmInventory.setUpdateBy(wmOrderDetail.getUpdateBy());
        wmInventory.setCreateTime(LocalDateTime.now());
        wmInventory.setUpdateTime(LocalDateTime.now());
        return wmInventory;
    }

    public void placing(List<WmInventory> wmInventory) {
        // 操作数据库
        wmInventory.forEach(this::placing);
    }

    public void placing(WmInventory wmInventory) {
        // 操作数据库
        wmInventoryMapper.insertOrUpdateInventory(wmInventory);
    }

    public void take(List<WmInventory> wmInventory) {
        // 操作数据库
        wmInventory.forEach(this::take);
    }

    public void take(WmInventory wmInventory) {
        // 操作数据库
        int transNum = wmInventoryMapper.decreaseInventory(wmInventory);
        // 判断是否成功
        if(transNum != 1) {
            throw new RuntimeException("库存不足");
        }
    }

}
