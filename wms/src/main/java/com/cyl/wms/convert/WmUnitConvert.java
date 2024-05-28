package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmUnit;
import com.cyl.wms.pojo.dto.WmUnitDTO;
import com.cyl.wms.pojo.vo.WmUnitVO;
import java.util.List;
/**
 * 单位  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmUnitConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmUnitDTO do2dto(WmUnit source);

    /**
     * @param source DTO
     * @return DO
     */
    WmUnit dto2do(WmUnitDTO source);

    List<WmUnitVO> dos2vos(List<WmUnit> list);
}
