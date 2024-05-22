package com.cyl.wms.pojo.query;

import java.math.BigDecimal;
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
public class GoodsQuery {
    @ApiModelProperty("编号 精确匹配")
    private String goodsNo;

    @ApiModelProperty("名称 精确匹配")
    private String goodsNameLike;

    @ApiModelProperty("品牌 精确匹配")
    private Long brandNo;

    @ApiModelProperty("规格 精确匹配")
    private String goodsSpec;

    @ApiModelProperty("分类 精确匹配")
    private String goodsType;

    @ApiModelProperty("大包装 精确匹配")
    private String maxUnit;

    @ApiModelProperty("大包装单价 精确匹配")
    private BigDecimal maxUnitPrice;

    @ApiModelProperty("中包装 精确匹配")
    private String aveUnit;

    @ApiModelProperty("中包装单价 精确匹配")
    private BigDecimal aveUnitPrice;

    @ApiModelProperty("小包装 精确匹配")
    private String minUnit;

    @ApiModelProperty("小包装单价 精确匹配")
    private BigDecimal minUnitPrice;

    @ApiModelProperty("大包装包含多少中包装 精确匹配")
    private Long maxAveNum;

    @ApiModelProperty("大包装包含多少小包装 精确匹配")
    private Long maxMinNum;

    @ApiModelProperty("中包装包含多少小包装 精确匹配")
    private Long aveMinNum;

    @ApiModelProperty("保质期 精确匹配")
    private Long shelfLife;

    @ApiModelProperty("保质期单位 精确匹配")
    private String shelfLifeUnit;

}
