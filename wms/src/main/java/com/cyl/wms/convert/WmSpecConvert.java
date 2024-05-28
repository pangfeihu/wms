package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmSpec;
import com.cyl.wms.pojo.dto.WmSpecDTO;
import com.cyl.wms.pojo.vo.WmSpecVO;
import java.util.List;
/**
 * 规格  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmSpecConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmSpecDTO do2dto(WmSpec source);

    /**
     * @param source DTO
     * @return DO
     */
    WmSpec dto2do(WmSpecDTO source);

    List<WmSpecVO> dos2vos(List<WmSpec> list);
}
