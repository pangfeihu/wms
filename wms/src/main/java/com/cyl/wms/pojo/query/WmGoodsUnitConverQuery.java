package com.cyl.wms.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品单位转换 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="商品单位转换 查询 对象")
@Data
public class WmGoodsUnitConverQuery {
    @ApiModelProperty("商品id 精确匹配")
    private Long goodsId;

    @ApiModelProperty("大包装单位id 精确匹配")
    private Long maxUnitId;

    @ApiModelProperty("小包装单位id 精确匹配")
    private Long minUnitId;

    @ApiModelProperty("比例 精确匹配")
    private Long ratio;

}
