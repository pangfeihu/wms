package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.Unit;
import com.cyl.wms.pojo.dto.UnitDTO;
import com.cyl.wms.pojo.vo.UnitVO;
import java.util.List;
/**
 * 单位  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface UnitConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    UnitDTO do2dto(Unit source);

    /**
     * @param source DTO
     * @return DO
     */
    Unit dto2do(UnitDTO source);

    List<UnitVO> dos2vos(List<Unit> list);
}
