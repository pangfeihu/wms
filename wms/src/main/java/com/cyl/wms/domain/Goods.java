package com.cyl.wms.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商品表对象 wms_goods
 * 
 * @author zcc
 */
@ApiModel(description="商品表对象")
@Data
@TableName("wms_goods")
public class Goods extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String goodsNo;

    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String goodsName;

    @ApiModelProperty("品牌")
    @Excel(name = "品牌")
    private Long brandNo;

    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private String goodsSpec;

    @ApiModelProperty("分类")
    @Excel(name = "分类")
    private String goodsType;

    @ApiModelProperty("大包装")
    @Excel(name = "大包装")
    private String maxUnit;

    @ApiModelProperty("大包装单价")
    @Excel(name = "大包装单价")
    private BigDecimal maxUnitPrice;

    @ApiModelProperty("中包装")
    @Excel(name = "中包装")
    private String aveUnit;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("中包装单价")
    @Excel(name = "中包装单价")
    private BigDecimal aveUnitPrice;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("小包装")
    @Excel(name = "小包装")
    private String minUnit;

    @ApiModelProperty("小包装单价")
    @Excel(name = "小包装单价")
    private BigDecimal minUnitPrice;

    @ApiModelProperty("大包装包含多少中包装")
    @Excel(name = "大包装包含多少中包装")
    private Long maxAveNum;

    @ApiModelProperty("大包装包含多少小包装")
    @Excel(name = "大包装包含多少小包装")
    private Long maxMinNum;

    @ApiModelProperty("中包装包含多少小包装")
    @Excel(name = "中包装包含多少小包装")
    private Long aveMinNum;

    @ApiModelProperty("保质期")
    @Excel(name = "保质期")
    private Long shelfLife;

    @ApiModelProperty("保质期单位")
    @Excel(name = "保质期单位")
    private String shelfLifeUnit;

}
