package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmOrderDetail;
import com.cyl.wms.pojo.dto.WmOrderDetailDTO;
import com.cyl.wms.pojo.vo.WmOrderDetailVO;
import java.util.List;
/**
 * 订单明细  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmOrderDetailConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmOrderDetailDTO do2dto(WmOrderDetail source);

    /**
     * @param source DTO
     * @return DO
     */
    WmOrderDetail dto2do(WmOrderDetailDTO source);

    List<WmOrderDetailVO> dos2vos(List<WmOrderDetail> list);
}
