package com.cyl.wms.pojo.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品单位表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="商品单位表 查询 对象")
@Data
public class WmGoodsUnitQuery {
    @ApiModelProperty("单位id 精确匹配")
    private Long unitId;

    @ApiModelProperty("商品id 精确匹配")
    private Long goodsId;

    @ApiModelProperty("销售单价 精确匹配")
    private BigDecimal saleUnitPrice;

    @ApiModelProperty("销售单价 精确匹配")
    private BigDecimal purchaseUnitPrice;

    @ApiModelProperty("活动单价 精确匹配")
    private BigDecimal promoUnitPrice;

}
