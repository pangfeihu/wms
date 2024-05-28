package com.cyl.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.wms.pojo.query.WmGoodsQuery;
import com.cyl.wms.pojo.vo.WmGoodsVO;
import org.apache.ibatis.annotations.Param;
import com.cyl.wms.domain.WmGoods;

/**
 * 商品表Mapper接口
 * 
 * @author zcc
 */
public interface WmGoodsMapper extends BaseMapper<WmGoods> {
    /**
     * 查询商品表列表
     *
     * @param wmGoods 商品表
     * @return 商品表集合
     */
    List<WmGoods> selectByEntity(WmGoods wmGoods);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    List<WmGoodsVO> selectListDetail(@Param("query") WmGoodsQuery query);

    List<WmGoodsVO> selectListByGoodsIds(@Param("ids") List<Long> ids);
}
