package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmUnit;

/**
 * 单位Mapper接口
 * 
 * @author zcc
 */
public interface WmUnitMapper extends BaseMapper<WmUnit> {
    /**
     * 查询单位列表
     *
     * @param wmUnit 单位
     * @return 单位集合
     */
    List<WmUnit> selectByEntity(WmUnit wmUnit);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
