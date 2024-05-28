package com.cyl.wms.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 库存记录 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="库存记录 查询 对象")
@Data
public class WmInventoryHistoryQuery {
    @ApiModelProperty("订单编号 精确匹配")
    private Long orderId;

    @ApiModelProperty("订单类型 精确匹配")
    private Integer orderType;

    @ApiModelProperty("单位编号 精确匹配")
    private Long unitId;

    @ApiModelProperty("商品编号 精确匹配")
    private Long goodsId;

    @ApiModelProperty("库存变化 精确匹配")
    private BigDecimal quantity;

    @ApiModelProperty("所属仓库 精确匹配")
    private Long warehouseId;

}
