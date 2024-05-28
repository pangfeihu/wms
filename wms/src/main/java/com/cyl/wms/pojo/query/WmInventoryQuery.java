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
public class WmInventoryQuery {
    @ApiModelProperty("商品编号 精确匹配")
    private Long goodsId;

    @ApiModelProperty("单位编号 精确匹配")
    private Long unitId;

    @ApiModelProperty("库存 精确匹配")
    private BigDecimal quantity;

    @ApiModelProperty("所属仓库 精确匹配")
    private Long warehouseId;

    @ApiModelProperty("所属库区 精确匹配")
    private Long areaId;

}
