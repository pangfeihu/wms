package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.Goods;

/**
 * 商品表Mapper接口
 * 
 * @author zcc
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 查询商品表列表
     *
     * @param goods 商品表
     * @return 商品表集合
     */
    List<Goods> selectByEntity(Goods goods);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
