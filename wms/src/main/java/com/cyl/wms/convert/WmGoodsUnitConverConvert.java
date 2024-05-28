package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmGoodsUnitConver;
import com.cyl.wms.pojo.dto.WmGoodsUnitConverDTO;
import com.cyl.wms.pojo.vo.WmGoodsUnitConverVO;
import java.util.List;
/**
 * 商品单位转换  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmGoodsUnitConverConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmGoodsUnitConverDTO do2dto(WmGoodsUnitConver source);

    /**
     * @param source DTO
     * @return DO
     */
    WmGoodsUnitConver dto2do(WmGoodsUnitConverDTO source);

    List<WmGoodsUnitConverVO> dos2vos(List<WmGoodsUnitConver> list);
}
