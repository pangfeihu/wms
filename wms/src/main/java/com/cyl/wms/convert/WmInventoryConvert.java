package com.cyl.wms.convert;

import com.cyl.wms.domain.WmOrderDetail;
import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmInventory;
import com.cyl.wms.pojo.dto.WmInventoryDTO;
import com.cyl.wms.pojo.vo.WmInventoryVO;
import java.util.List;
/**
 * 库存  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmInventoryConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmInventoryDTO do2dto(WmInventory source);

    /**
     * @param source DTO
     * @return DO
     */
    WmInventory dto2do(WmInventoryDTO source);

    List<WmInventoryVO> dos2vos(List<WmInventory> list);

    List<WmInventory> order2Entity(List<WmOrderDetail> list);

}
