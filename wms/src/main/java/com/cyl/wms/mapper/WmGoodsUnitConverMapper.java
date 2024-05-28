package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmGoodsUnitConver;

/**
 * 商品单位转换Mapper接口
 * 
 * @author zcc
 */
public interface WmGoodsUnitConverMapper extends BaseMapper<WmGoodsUnitConver> {
    /**
     * 查询商品单位转换列表
     *
     * @param wmGoodsUnitConver 商品单位转换
     * @return 商品单位转换集合
     */
    List<WmGoodsUnitConver> selectByEntity(WmGoodsUnitConver wmGoodsUnitConver);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
