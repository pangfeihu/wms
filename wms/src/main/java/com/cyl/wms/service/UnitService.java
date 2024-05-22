package com.cyl.wms.service;

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
import com.cyl.wms.mapper.UnitMapper;
import com.cyl.wms.domain.Unit;
import com.cyl.wms.pojo.query.UnitQuery;

/**
 * 单位Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class UnitService {
    @Autowired
    private UnitMapper unitMapper;

    /**
     * 查询单位
     *
     * @param id 单位主键
     * @return 单位
     */
    public Unit selectById(Long id) {
        return unitMapper.selectById(id);
    }

    /**
     * 查询单位列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 单位
     */
    public List<Unit> selectList(UnitQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Unit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long id = query.getId();
        if (id != null) {
            qw.eq("id", id);
        }
        String unitNameLike = query.getUnitNameLike();
        if (!StringUtils.isEmpty(unitNameLike)) {
            qw.like("unit_name", unitNameLike);
        }
        return unitMapper.selectList(qw);
    }

    public List<Unit> selectList() {
        QueryWrapper<Unit> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        return unitMapper.selectList(qw);
    }

    /**
     * 新增单位
     *
     * @param unit 单位
     * @return 结果
     */
    public int insert(Unit unit) {
        unit.setCreateTime(LocalDateTime.now());
        return unitMapper.insert(unit);
    }

    /**
     * 修改单位
     *
     * @param unit 单位
     * @return 结果
     */
    public int update(Unit unit) {
        return unitMapper.updateById(unit);
    }

    /**
     * 批量删除单位
     *
     * @param ids 需要删除的单位主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return unitMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除单位信息
     *
     * @param id 单位主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return unitMapper.updateDelFlagByIds(ids);
    }
}
