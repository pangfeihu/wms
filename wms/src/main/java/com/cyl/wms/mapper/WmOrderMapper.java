package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmOrder;

/**
 * 订单Mapper接口
 * 
 * @author zcc
 */
public interface WmOrderMapper extends BaseMapper<WmOrder> {
    /**
     * 查询订单列表
     *
     * @param wmOrder 订单
     * @return 订单集合
     */
    List<WmOrder> selectByEntity(WmOrder wmOrder);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
