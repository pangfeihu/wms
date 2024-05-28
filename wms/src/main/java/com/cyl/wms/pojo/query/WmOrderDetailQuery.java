package com.cyl.wms.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单明细 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单明细 查询 对象")
@Data
public class WmOrderDetailQuery {
    @ApiModelProperty("订单编号 精确匹配")
    private Long orderId;

    @ApiModelProperty("商品编号 精确匹配")
    private Long goodsId;

    @ApiModelProperty("计划数量 精确匹配")
    private BigDecimal planQuantity;

    @ApiModelProperty("实际数量 精确匹配")
    private BigDecimal realQuantity;

    @ApiModelProperty("单位 精确匹配")
    private Long unit;

    @ApiModelProperty("类型 精确匹配")
    private Long type;

    @ApiModelProperty("金额 精确匹配")
    private BigDecimal money;

}
