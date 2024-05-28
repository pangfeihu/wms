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
import com.cyl.wms.mapper.WmSpecMapper;
import com.cyl.wms.domain.WmSpec;
import com.cyl.wms.pojo.query.WmSpecQuery;

/**
 * 规格Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmSpecService extends ServiceImpl<WmSpecMapper, WmSpec> {
    @Autowired
    private WmSpecMapper wmSpecMapper;

    /**
     * 查询规格
     *
     * @param id 规格主键
     * @return 规格
     */
    public WmSpec selectById(Long id) {
        return wmSpecMapper.selectById(id);
    }

    /**
     * 查询规格列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 规格
     */
    public List<WmSpec> selectList(WmSpecQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmSpec> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String specNameLike = query.getSpecNameLike();
        if (!StringUtils.isEmpty(specNameLike)) {
            qw.like("spec_name", specNameLike);
        }
        String specDesc = query.getSpecDesc();
        if (!StringUtils.isEmpty(specDesc)) {
            qw.eq("spec_desc", specDesc);
        }
        return wmSpecMapper.selectList(qw);
    }

    /**
     * 新增规格
     *
     * @param wmSpec 规格
     * @return 结果
     */
    public int insert(WmSpec wmSpec) {
        wmSpec.setDelFlag(0L);
        wmSpec.setCreateTime(LocalDateTime.now());
        return wmSpecMapper.insert(wmSpec);
    }

    /**
     * 修改规格
     *
     * @param wmSpec 规格
     * @return 结果
     */
    public int update(WmSpec wmSpec) {
        return wmSpecMapper.updateById(wmSpec);
    }

    /**
     * 批量删除规格
     *
     * @param ids 需要删除的规格主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmSpecMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除规格信息
     *
     * @param id 规格主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmSpecMapper.updateDelFlagByIds(ids);
    }


}
