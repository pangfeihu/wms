package com.cyl.wms.convert;

import org.mapstruct.Mapper;
import com.cyl.wms.domain.Stock;
import com.cyl.wms.pojo.dto.StockDTO;
import com.cyl.wms.pojo.vo.StockVO;
import java.util.List;
/**
 * 库存  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface StockConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    StockDTO do2dto(Stock source);

    /**
     * @param source DTO
     * @return DO
     */
    Stock dto2do(StockDTO source);

    List<StockVO> dos2vos(List<Stock> list);
}
