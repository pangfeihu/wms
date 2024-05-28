package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmInventory;

/**
 * 库存Mapper接口
 * 
 * @author zcc
 */
public interface WmInventoryMapper extends BaseMapper<WmInventory> {
    /**
     * 查询库存列表
     *
     * @param wmInventory 库存
     * @return 库存集合
     */
    List<WmInventory> selectByEntity(WmInventory wmInventory);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);


    int insertOrUpdateInventory(@Param("wmInventory") WmInventory wmInventory);

    int decreaseInventory(@Param("wmInventory")WmInventory wmInventory);
}
