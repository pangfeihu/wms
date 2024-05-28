package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmGoodsUnit;

/**
 * 商品单位表Mapper接口
 * 
 * @author zcc
 */
public interface WmGoodsUnitMapper extends BaseMapper<WmGoodsUnit> {
    /**
     * 查询商品单位表列表
     *
     * @param wmGoodsUnit 商品单位表
     * @return 商品单位表集合
     */
    List<WmGoodsUnit> selectByEntity(WmGoodsUnit wmGoodsUnit);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
