package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.pojo.dto.WmGoodsUnitDTO;
import com.cyl.wms.pojo.vo.WmGoodsUnitVO;
import java.util.List;
/**
 * 商品单位表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmGoodsUnitConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmGoodsUnitDTO do2dto(WmGoodsUnit source);

    /**
     * @param source DTO
     * @return DO
     */
    WmGoodsUnit dto2do(WmGoodsUnitDTO source);

    List<WmGoodsUnitVO> dos2vos(List<WmGoodsUnit> list);
}
