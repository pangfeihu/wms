package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmWarehouse;

/**
 * 仓库Mapper接口
 * 
 * @author zcc
 */
public interface WmWarehouseMapper extends BaseMapper<WmWarehouse> {
    /**
     * 查询仓库列表
     *
     * @param wmWarehouse 仓库
     * @return 仓库集合
     */
    List<WmWarehouse> selectByEntity(WmWarehouse wmWarehouse);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
