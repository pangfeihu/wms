package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.Stock;

/**
 * 库存Mapper接口
 * 
 * @author zcc
 */
public interface StockMapper extends BaseMapper<Stock> {
    /**
     * 查询库存列表
     *
     * @param stock 库存
     * @return 库存集合
     */
    List<Stock> selectByEntity(Stock stock);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
