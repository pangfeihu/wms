package com.cyl.wms.service;

import java.time.LocalDateTime;
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
import com.cyl.wms.mapper.WmGoodsUnitConverMapper;
import com.cyl.wms.domain.WmGoodsUnitConver;
import com.cyl.wms.pojo.query.WmGoodsUnitConverQuery;

/**
 * 商品单位转换Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmGoodsUnitConverService extends ServiceImpl<WmGoodsUnitConverMapper, WmGoodsUnitConver> {
    @Autowired
    private WmGoodsUnitConverMapper wmGoodsUnitConverMapper;

    /**
     * 查询商品单位转换
     *
     * @param id 商品单位转换主键
     * @return 商品单位转换
     */
    public WmGoodsUnitConver selectById(Long id) {
        return wmGoodsUnitConverMapper.selectById(id);
    }

    /**
     * 查询商品单位转换列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 商品单位转换
     */
    public List<WmGoodsUnitConver> selectList(WmGoodsUnitConverQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmGoodsUnitConver> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long goodsId = query.getGoodsId();
        if (goodsId != null) {
            qw.eq("goods_id", goodsId);
        }
        Long maxUnitId = query.getMaxUnitId();
        if (maxUnitId != null) {
            qw.eq("max_unit_id", maxUnitId);
        }
        Long minUnitId = query.getMinUnitId();
        if (minUnitId != null) {
            qw.eq("min_unit_id", minUnitId);
        }
        Long ratio = query.getRatio();
        if (ratio != null) {
            qw.eq("ratio", ratio);
        }
        return wmGoodsUnitConverMapper.selectList(qw);
    }

    /**
     * 新增商品单位转换
     *
     * @param wmGoodsUnitConver 商品单位转换
     * @return 结果
     */
    public int insert(WmGoodsUnitConver wmGoodsUnitConver) {
        wmGoodsUnitConver.setDelFlag(0L);
        wmGoodsUnitConver.setCreateTime(LocalDateTime.now());
        return wmGoodsUnitConverMapper.insert(wmGoodsUnitConver);
    }

    /**
     * 修改商品单位转换
     *
     * @param wmGoodsUnitConver 商品单位转换
     * @return 结果
     */
    public int update(WmGoodsUnitConver wmGoodsUnitConver) {
        return wmGoodsUnitConverMapper.updateById(wmGoodsUnitConver);
    }

    /**
     * 批量删除商品单位转换
     *
     * @param ids 需要删除的商品单位转换主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmGoodsUnitConverMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除商品单位转换信息
     *
     * @param id 商品单位转换主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmGoodsUnitConverMapper.updateDelFlagByIds(ids);
    }

    public void insertBatch(List<WmGoodsUnitConver> goodsUnitConverList) {
        // 设置默认值
        goodsUnitConverList.forEach(line -> {
            line.setDelFlag(0L);
            line.setCreateTime(LocalDateTime.now());
        });
        // 保存数据信息
        this.saveBatch(goodsUnitConverList);
    }
}
