package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmSpec;

/**
 * 规格Mapper接口
 * 
 * @author zcc
 */
public interface WmSpecMapper extends BaseMapper<WmSpec> {
    /**
     * 查询规格列表
     *
     * @param wmSpec 规格
     * @return 规格集合
     */
    List<WmSpec> selectByEntity(WmSpec wmSpec);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
