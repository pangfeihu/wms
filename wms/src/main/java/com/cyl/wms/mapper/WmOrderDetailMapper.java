package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmOrderDetail;

/**
 * 订单明细Mapper接口
 * 
 * @author zcc
 */
public interface WmOrderDetailMapper extends BaseMapper<WmOrderDetail> {
    /**
     * 查询订单明细列表
     *
     * @param wmOrderDetail 订单明细
     * @return 订单明细集合
     */
    List<WmOrderDetail> selectByEntity(WmOrderDetail wmOrderDetail);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
