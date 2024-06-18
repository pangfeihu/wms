package com.cyl.wms.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmUnitMapper;
import com.cyl.wms.domain.WmUnit;
import com.cyl.wms.pojo.query.WmUnitQuery;

/**
 * 单位Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmUnitService {
    @Autowired
    private WmUnitMapper wmUnitMapper;

    /**
     * 查询单位
     *
     * @param id 单位主键
     * @return 单位
     */
    public WmUnit selectById(Long id) {
        return wmUnitMapper.selectById(id);
    }

    /**
     * 查询单位列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 单位
     */
    public List<WmUnit> selectList(WmUnitQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmUnit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String unitNameLike = query.getUnitNameLike();
        if (!StringUtils.isEmpty(unitNameLike)) {
            qw.like("unit_name", unitNameLike);
        }
        return wmUnitMapper.selectList(qw);
    }

    public Map<Long,WmUnit> selectMap() {
        // 查询所有单位信息
        List<WmUnit> wmUnits = selectList(new WmUnitQuery(),null);
        // 进行分组
        if(CollUtil.isEmpty(wmUnits)){
            return new HashMap<>();
        }
        // 分组
        return wmUnits.stream()
                .collect(Collectors.toMap(
                        row -> row.getId() , // 键映射器，使用id和unitId的组合作为键
                        unit -> unit,                               // 值映射器，直接使用WmGoodsUnit对象
                        (existing, replacement) -> existing          // 合并函数，如果有重复的key，选择保留的value
                ));
    }

    /**
     * 新增单位
     *
     * @param wmUnit 单位
     * @return 结果
     */
    public int insert(WmUnit wmUnit) {
        wmUnit.setDelFlag(0L);
        wmUnit.setCreateTime(LocalDateTime.now());
        return wmUnitMapper.insert(wmUnit);
    }

    /**
     * 修改单位
     *
     * @param wmUnit 单位
     * @return 结果
     */
    public int update(WmUnit wmUnit) {
        return wmUnitMapper.updateById(wmUnit);
    }

    /**
     * 批量删除单位
     *
     * @param ids 需要删除的单位主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmUnitMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除单位信息
     *
     * @param id 单位主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmUnitMapper.updateDelFlagByIds(ids);
    }
}
