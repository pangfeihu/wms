package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmGoods;
import com.cyl.wms.pojo.dto.WmGoodsDTO;
import com.cyl.wms.pojo.vo.WmGoodsVO;
import java.util.List;
/**
 * 商品表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmGoodsConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmGoodsDTO do2dto(WmGoods source);

    /**
     * @param source DTO
     * @return DO
     */
    WmGoods dto2do(WmGoodsDTO source);

    List<WmGoodsVO> dos2vos(List<WmGoods> list);
}
