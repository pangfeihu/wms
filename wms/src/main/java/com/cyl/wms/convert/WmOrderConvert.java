package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmOrder;
import com.cyl.wms.pojo.dto.WmOrderDTO;
import com.cyl.wms.pojo.vo.WmOrderVO;
import java.util.List;
/**
 * 订单  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmOrderConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmOrderDTO do2dto(WmOrder source);

    /**
     * @param source DTO
     * @return DO
     */
    WmOrder dto2do(WmOrderDTO source);

    List<WmOrderVO> dos2vos(List<WmOrder> list);
}
