package com.cyl.wms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyl.wms.domain.WmSpec;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmGoodsUnitMapper;
import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.pojo.query.WmGoodsUnitQuery;

/**
 * 商品单位表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmGoodsUnitService extends ServiceImpl<WmGoodsUnitMapper, WmGoodsUnit> {
    @Autowired
    private WmGoodsUnitMapper wmGoodsUnitMapper;

    @Autowired
    private WmSpecService wmSpecService;

    /**
     * 查询商品单位表
     *
     * @param id 商品单位表主键
     * @return 商品单位表
     */
    public WmGoodsUnit selectById(Long id) {
        return wmGoodsUnitMapper.selectById(id);
    }

    /**
     * 查询商品单位表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 商品单位表
     */
    public List<WmGoodsUnit> selectList(WmGoodsUnitQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long unitId = query.getUnitId();
        if (unitId != null) {
            qw.eq("unit_id", unitId);
        }
        Long goodsId = query.getGoodsId();
        if (goodsId != null) {
            qw.eq("goods_id", goodsId);
        }
        BigDecimal saleUnitPrice = query.getSaleUnitPrice();
        if (saleUnitPrice != null) {
            qw.eq("sale_unit_price", saleUnitPrice);
        }
        BigDecimal purchaseUnitPrice = query.getPurchaseUnitPrice();
        if (purchaseUnitPrice != null) {
            qw.eq("purchase_unit_price", purchaseUnitPrice);
        }
        BigDecimal promoUnitPrice = query.getPromoUnitPrice();
        if (promoUnitPrice != null) {
            qw.eq("promo_unit_price", promoUnitPrice);
        }
        return wmGoodsUnitMapper.selectList(qw);
    }

    /**
     * 新增商品单位表
     *
     * @param wmGoodsUnit 商品单位表
     * @return 结果
     */
    public int insert(WmGoodsUnit wmGoodsUnit) {
        wmGoodsUnit.setDelFlag(0L);
        wmGoodsUnit.setCreateTime(LocalDateTime.now());
        return wmGoodsUnitMapper.insert(wmGoodsUnit);
    }

    public void insertBatch(Long goodsId,List<WmGoodsUnit> wmGoodsUnits) {
        // 获取所有的基础信息
        List<Long> specIds = wmGoodsUnits.stream().map(WmGoodsUnit::getSpecId).collect(Collectors.toList());
        // 获取商品规格
        Map<Long, WmSpec> wmSpecMap = wmSpecService.listByIds(specIds).stream().collect(Collectors.toMap(WmSpec::getId, wmSpec -> wmSpec));
        // 赋默认值
        for (WmGoodsUnit line : wmGoodsUnits) {
            // 获取商品规格
            WmSpec wmSpec = wmSpecMap.get(line.getSpecId());
            // 判断商品规格是否存在
            if(null == wmGoodsUnits) {
                throw new RuntimeException("商品规格不存在");
            }
            line.setSpecName(wmSpec.getSpecName());
            line.setGoodsId(goodsId);
            line.setDelFlag(0L);
            line.setCreateTime(LocalDateTime.now());
        }


        wmGoodsUnits.forEach(line -> {

        });
        // 保存数据
        this.saveBatch(wmGoodsUnits);
    }

    /**
     * 修改商品单位表
     *
     * @param wmGoodsUnit 商品单位表
     * @return 结果
     */
    public int update(WmGoodsUnit wmGoodsUnit) {
        return wmGoodsUnitMapper.updateById(wmGoodsUnit);
    }

    /**
     * 批量删除商品单位表
     *
     * @param ids 需要删除的商品单位表主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmGoodsUnitMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除商品单位表信息
     *
     * @param id 商品单位表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmGoodsUnitMapper.updateDelFlagByIds(ids);
    }

    public List<WmGoodsUnit> selectByGoodsId(Long goodsId) {
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        qw.eq("goods_id", goodsId);
        return wmGoodsUnitMapper.selectList(qw);
    }

    public List<WmGoodsUnit> selectByGoodsId(List<Long> goodsIds) {
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        qw.in("goods_id", goodsIds);
        return wmGoodsUnitMapper.selectList(qw);
    }

    public Map<String,WmGoodsUnit> selectMapDetail(List<Long> goodsIds) {
        // 获取集合信息
        List<WmGoodsUnit> goodsDetails = this.selectByGoodsId(goodsIds);
        // 判断是否为空
        if(CollUtil.isEmpty(goodsDetails)) {
            // 返回默认值
            return new HashMap<>();
        }
        // 转换为商品明细表
        return goodsDetails.stream()
                .collect(Collectors.toMap(
                        row -> row.getGoodsId() + "_" + row.getUnitId(), // 键映射器，使用id和unitId的组合作为键
                        unit -> unit,                               // 值映射器，直接使用WmGoodsUnit对象
                        (existing, replacement) -> existing          // 合并函数，如果有重复的key，选择保留的value
                ));
    }


    public void deleteByGoodsId(Long goodsId) {
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        qw.eq("goods_id", goodsId);
        wmGoodsUnitMapper.delete(qw);
    }

    public void deleteByGoodsIds(List<Long> goodsIds) {
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        qw.in("goods_id", goodsIds);
        wmGoodsUnitMapper.delete(qw);
    }

    public List<WmGoodsUnit> selectDetailByGoodsId(List<Long> goodsIds) {
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        qw.in("goods_id", goodsIds);
        // 获取商品明细信息
        List<WmGoodsUnit> wmGoodsUnits = wmGoodsUnitMapper.selectList(qw);
        // 查询所有商品信息
        return wmGoodsUnits;
    }

    public WmGoodsUnit selectByUnitId(Long goodsId, Long unitId) {
        QueryWrapper<WmGoodsUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        qw.eq("goods_id", goodsId);
        qw.eq("unit_id", unitId);
        // 获取商品明细信息
        return wmGoodsUnitMapper.selectOne(qw);
    }
}
