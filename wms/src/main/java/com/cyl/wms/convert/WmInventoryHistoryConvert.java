package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmInventoryHistory;
import com.cyl.wms.pojo.dto.WmInventoryHistoryDTO;
import com.cyl.wms.pojo.vo.WmInventoryHistoryVO;
import java.util.List;
/**
 * 库存记录  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmInventoryHistoryConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmInventoryHistoryDTO do2dto(WmInventoryHistory source);

    /**
     * @param source DTO
     * @return DO
     */
    WmInventoryHistory dto2do(WmInventoryHistoryDTO source);

    List<WmInventoryHistoryVO> dos2vos(List<WmInventoryHistory> list);
}
