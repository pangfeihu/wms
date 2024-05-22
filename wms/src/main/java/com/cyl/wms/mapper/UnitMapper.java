package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.Unit;

/**
 * 单位Mapper接口
 * 
 * @author zcc
 */
public interface UnitMapper extends BaseMapper<Unit> {
    /**
     * 查询单位列表
     *
     * @param unit 单位
     * @return 单位集合
     */
    List<Unit> selectByEntity(Unit unit);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
