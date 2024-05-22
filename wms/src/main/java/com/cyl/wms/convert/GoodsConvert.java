package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.Goods;
import com.cyl.wms.pojo.dto.GoodsDTO;
import com.cyl.wms.pojo.vo.GoodsVO;
import java.util.List;
/**
 * 商品表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface GoodsConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    GoodsDTO do2dto(Goods source);

    /**
     * @param source DTO
     * @return DO
     */
    Goods dto2do(GoodsDTO source);

    List<GoodsVO> dos2vos(List<Goods> list);
}
