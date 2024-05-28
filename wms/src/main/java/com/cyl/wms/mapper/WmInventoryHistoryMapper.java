package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmInventoryHistory;

/**
 * 库存记录Mapper接口
 * 
 * @author zcc
 */
public interface WmInventoryHistoryMapper extends BaseMapper<WmInventoryHistory> {
    /**
     * 查询库存记录列表
     *
     * @param wmInventoryHistory 库存记录
     * @return 库存记录集合
     */
    List<WmInventoryHistory> selectByEntity(WmInventoryHistory wmInventoryHistory);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
