package com.cyl.wms.service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.mapper.WmGoodsUnitMapper;
import com.cyl.wms.pojo.vo.WmGoodsVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmGoodsMapper;
import com.cyl.wms.domain.WmGoods;
import com.cyl.wms.pojo.query.WmGoodsQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmGoodsService {
    @Autowired
    private WmGoodsMapper wmGoodsMapper;
    @Autowired
    private WmGoodsUnitService wmGoodsUnitService;
    @Autowired
    private WmGoodsUnitConverService wmGoodsUnitConverService;

    /**
     * 查询商品表
     *
     * @param id 商品表主键
     * @return 商品表
     */
    public WmGoods selectById(Long id) {
        // 获取商品信息
        WmGoods wmGoods = wmGoodsMapper.selectById(id);
        // 判断是否存在
        if(null == wmGoods) {
            return null;
        }
        // 获取包装信息
        List<WmGoodsUnit> wmGoodsUnits = wmGoodsUnitService.selectByGoodsId(id);
        // 组装信息
        wmGoods.setDetails(wmGoodsUnits);
        return wmGoods;
    }

    /**
     * 查询商品表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 商品表
     */
    public List<WmGoods> selectList(WmGoodsQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmGoods> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String goodsNo = query.getGoodsNo();
        if (!StringUtils.isEmpty(goodsNo)) {
            qw.eq("goods_no", goodsNo);
        }
        String goodsNameLike = query.getGoodsNameLike();
        if (!StringUtils.isEmpty(goodsNameLike)) {
            qw.like("goods_name", goodsNameLike);
        }
        Long supplierId = query.getSupplierId();
        if (supplierId != null) {
            qw.eq("supplier_id", supplierId);
        }
        Long goodsTypeId = query.getGoodsTypeId();
        if (goodsTypeId != null) {
            qw.eq("goods_type_id", goodsTypeId);
        }
        Long shelfLife = query.getShelfLife();
        if (shelfLife != null) {
            qw.eq("shelf_life", shelfLife);
        }
        String shelfLifeUnit = query.getShelfLifeUnit();
        if (!StringUtils.isEmpty(shelfLifeUnit)) {
            qw.eq("shelf_life_unit", shelfLifeUnit);
        }
        return wmGoodsMapper.selectList(qw);
    }

    /**
     * 新增商品表
     *
     * @param wmGoods 商品表
     * @return 结果
     */
    @Transactional
    public int insert(WmGoods wmGoods) {
        // 设置商品默认值
        LocalDateTime now = LocalDateTime.now();
        wmGoods.setDelFlag(0);
        wmGoods.setCreateTime(now);
        // 保存到数据库当中
        int insert = wmGoodsMapper.insert(wmGoods);
        // 保存商品单位
        wmGoodsUnitService.insertBatch(wmGoods.getId(),wmGoods.getDetails());
        // 保存商品信息
        return insert;
    }

    /**
     * 修改商品表
     *
     * @param wmGoods 商品表
     * @return 结果
     */
    public int update(WmGoods wmGoods) {
        // 修改商品信息
        int result = wmGoodsMapper.updateById(wmGoods);
        // 获取商品明细信息
        List<WmGoodsUnit> details = wmGoods.getDetails();
        // 如果为空,则删除所有商品明细
        if(CollUtil.isEmpty(details)) {
            // 删除所有商品明细
            wmGoodsUnitService.deleteByGoodsId(wmGoods.getId());
            // 返回结果
            return result;
        }
        // 修改商品明细信息
        wmGoodsUnitService.saveOrUpdateBatch(details);
        return result;
    }

    /**
     * 批量删除商品表
     *
     * @param ids 需要删除的商品表主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        // 判断是否为空
        if(ids == null || ids.length == 0) {
            throw new RuntimeException("商品编号为空");
        }
        // 转换为集合
        List<Long> goodsIds = Arrays.stream(ids).collect(Collectors.toList());
        // 直接删除
        int result = wmGoodsMapper.deleteBatchIds(goodsIds);
        // 删除明细信息
        wmGoodsUnitService.deleteByGoodsIds(goodsIds);
        // 返回结果信息
        return result;
    }

    /**
     * 删除商品表信息
     *
     * @param id 商品表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmGoodsMapper.updateDelFlagByIds(ids);
    }

    public List<WmGoodsVO> selectListDetail(WmGoodsQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        return wmGoodsMapper.selectListDetail(query);
    }

    public List<WmGoodsVO> selectListDetail(List<Long> ids) {
        return wmGoodsMapper.selectListByGoodsIds(ids);
    }

    public Map<String, WmGoodsVO> selectMapDetail(List<Long> ids) {
        // 获取商品明细集合
        List<WmGoodsVO> wmGoodsVOS = this.selectListDetail(ids);
        // 判断是否为空
        if(CollUtil.isEmpty(wmGoodsVOS)) {
            return new HashMap<>();
        }
        // 转换为map表
        return wmGoodsVOS.stream()
                .collect(Collectors.toMap(
                        row -> row.getId() + "_" + row.getUnitId(), // 键映射器，使用id和unitId的组合作为键
                        unit -> unit,                               // 值映射器，直接使用WmGoodsUnit对象
                        (existing, replacement) -> existing          // 合并函数，如果有重复的key，选择保留的value
                ));
    }
}
