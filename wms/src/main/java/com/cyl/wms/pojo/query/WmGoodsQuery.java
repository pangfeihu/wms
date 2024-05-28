package com.cyl.wms.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="商品表 查询 对象")
@Data
public class WmGoodsQuery {
    @ApiModelProperty("编号 精确匹配")
    private String goodsNo;

    @ApiModelProperty("名称 精确匹配")
    private String goodsNameLike;

    @ApiModelProperty("供应商编号 精确匹配")
    private Long supplierId;

    @ApiModelProperty("分类 精确匹配")
    private Long goodsTypeId;

    @ApiModelProperty("保质期 精确匹配")
    private Long shelfLife;

    @ApiModelProperty("保质期单位 精确匹配")
    private String shelfLifeUnit;

}
