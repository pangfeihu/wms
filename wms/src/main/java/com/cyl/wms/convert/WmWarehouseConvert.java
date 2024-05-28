package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.WmWarehouse;
import com.cyl.wms.pojo.dto.WmWarehouseDTO;
import com.cyl.wms.pojo.vo.WmWarehouseVO;
import java.util.List;
/**
 * 仓库  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WmWarehouseConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    WmWarehouseDTO do2dto(WmWarehouse source);

    /**
     * @param source DTO
     * @return DO
     */
    WmWarehouse dto2do(WmWarehouseDTO source);

    List<WmWarehouseVO> dos2vos(List<WmWarehouse> list);
}
