package com.cyl.wms.pojo.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 库存 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="库存 查询 对象")
@Data
public class StockQuery {
    @ApiModelProperty("所属仓库 精确匹配")
    private Long warehouseId;

    @ApiModelProperty("商品编号 精确匹配")
    private Long goodsId;

    @ApiModelProperty("生产日期 精确匹配")
    private LocalDateTime prodDate;

    @ApiModelProperty("大包装库存 精确匹配")
    private BigDecimal maxQuantity;

    @ApiModelProperty("中包装库存 精确匹配")
    private BigDecimal aveQuantity;

    @ApiModelProperty("小包装库存 精确匹配")
    private BigDecimal minQuantity;

}
