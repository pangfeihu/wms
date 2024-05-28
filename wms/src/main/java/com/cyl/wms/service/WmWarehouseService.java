package com.cyl.wms.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.WmWarehouseMapper;
import com.cyl.wms.domain.WmWarehouse;
import com.cyl.wms.pojo.query.WmWarehouseQuery;

/**
 * 仓库Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WmWarehouseService {
    @Autowired
    private WmWarehouseMapper wmWarehouseMapper;

    /**
     * 查询仓库
     *
     * @param id 仓库主键
     * @return 仓库
     */
    public WmWarehouse selectById(Long id) {
        return wmWarehouseMapper.selectById(id);
    }

    /**
     * 查询仓库列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 仓库
     */
    public List<WmWarehouse> selectList(WmWarehouseQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WmWarehouse> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String warehouseNo = query.getWarehouseNo();
        if (!StringUtils.isEmpty(warehouseNo)) {
            qw.eq("warehouse_no", warehouseNo);
        }
        String warehouseNameLike = query.getWarehouseNameLike();
        if (!StringUtils.isEmpty(warehouseNameLike)) {
            qw.like("warehouse_name", warehouseNameLike);
        }
        return wmWarehouseMapper.selectList(qw);
    }

    /**
     * 新增仓库
     *
     * @param wmWarehouse 仓库
     * @return 结果
     */
    public int insert(WmWarehouse wmWarehouse) {
        wmWarehouse.setDelFlag(0);
        wmWarehouse.setCreateTime(LocalDateTime.now());
        return wmWarehouseMapper.insert(wmWarehouse);
    }

    /**
     * 修改仓库
     *
     * @param wmWarehouse 仓库
     * @return 结果
     */
    public int update(WmWarehouse wmWarehouse) {
        return wmWarehouseMapper.updateById(wmWarehouse);
    }

    /**
     * 批量删除仓库
     *
     * @param ids 需要删除的仓库主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return wmWarehouseMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除仓库信息
     *
     * @param id 仓库主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return wmWarehouseMapper.updateDelFlagByIds(ids);
    }
}
