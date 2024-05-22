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
import com.cyl.wms.mapper.GoodsMapper;
import com.cyl.wms.domain.Goods;
import com.cyl.wms.pojo.query.GoodsQuery;

/**
 * 商品表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询商品表
     *
     * @param id 商品表主键
     * @return 商品表
     */
    public Goods selectById(Long id) {
        return goodsMapper.selectById(id);
    }

    /**
     * 查询商品表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 商品表
     */
    public List<Goods> selectList(GoodsQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String goodsNo = query.getGoodsNo();
        if (!StringUtils.isEmpty(goodsNo)) {
            qw.eq("goods_no", goodsNo);
        }
        String goodsNameLike = query.getGoodsNameLike();
        if (!StringUtils.isEmpty(goodsNameLike)) {
            qw.like("goods_name", goodsNameLike);
        }
        Long brandNo = query.getBrandNo();
        if (brandNo != null) {
            qw.eq("brand_no", brandNo);
        }
        String goodsSpec = query.getGoodsSpec();
        if (!StringUtils.isEmpty(goodsSpec)) {
            qw.eq("goods_spec", goodsSpec);
        }
        String goodsType = query.getGoodsType();
        if (!StringUtils.isEmpty(goodsType)) {
            qw.eq("goods_type", goodsType);
        }
        String maxUnit = query.getMaxUnit();
        if (!StringUtils.isEmpty(maxUnit)) {
            qw.eq("max_unit", maxUnit);
        }
        BigDecimal maxUnitPrice = query.getMaxUnitPrice();
        if (maxUnitPrice != null) {
            qw.eq("max_unit_price", maxUnitPrice);
        }
        String aveUnit = query.getAveUnit();
        if (!StringUtils.isEmpty(aveUnit)) {
            qw.eq("ave_unit", aveUnit);
        }
        BigDecimal aveUnitPrice = query.getAveUnitPrice();
        if (aveUnitPrice != null) {
            qw.eq("ave_unit_price", aveUnitPrice);
        }
        String minUnit = query.getMinUnit();
        if (!StringUtils.isEmpty(minUnit)) {
            qw.eq("min_unit", minUnit);
        }
        BigDecimal minUnitPrice = query.getMinUnitPrice();
        if (minUnitPrice != null) {
            qw.eq("min_unit_price", minUnitPrice);
        }
        Long maxAveNum = query.getMaxAveNum();
        if (maxAveNum != null) {
            qw.eq("max_ave_num", maxAveNum);
        }
        Long maxMinNum = query.getMaxMinNum();
        if (maxMinNum != null) {
            qw.eq("max_min_num", maxMinNum);
        }
        Long aveMinNum = query.getAveMinNum();
        if (aveMinNum != null) {
            qw.eq("ave_min_num", aveMinNum);
        }
        Long shelfLife = query.getShelfLife();
        if (shelfLife != null) {
            qw.eq("shelf_life", shelfLife);
        }
        String shelfLifeUnit = query.getShelfLifeUnit();
        if (!StringUtils.isEmpty(shelfLifeUnit)) {
            qw.eq("shelf_life_unit", shelfLifeUnit);
        }
        return goodsMapper.selectList(qw);
    }

    /**
     * 新增商品表
     *
     * @param goods 商品表
     * @return 结果
     */
    public int insert(Goods goods) {
        goods.setDelFlag(0);
        goods.setCreateTime(LocalDateTime.now());
        return goodsMapper.insert(goods);
    }

    /**
     * 修改商品表
     *
     * @param goods 商品表
     * @return 结果
     */
    public int update(Goods goods) {
        return goodsMapper.updateById(goods);
    }

    /**
     * 批量删除商品表
     *
     * @param ids 需要删除的商品表主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return goodsMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除商品表信息
     *
     * @param id 商品表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return goodsMapper.updateDelFlagByIds(ids);
    }
}
