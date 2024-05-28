package com.cyl.wms.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品表 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmGoodsVO extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String goodsNo;

    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String goodsName;

    @ApiModelProperty("供应商编号")
    @Excel(name = "供应商编号")
    private Long supplierId;

    @ApiModelProperty("供应商名称")
    @Excel(name = "供应商名称")
    private String supplierName;

    @ApiModelProperty("规格编号")
    @Excel(name = "规格编号")
    private Long specId;

    @ApiModelProperty("规格名称")
    @Excel(name = "规格名称")
    private String specName;

    @ApiModelProperty("分类")
    @Excel(name = "分类")
    private Long goodsTypeId;

    @ApiModelProperty("保质期")
    @Excel(name = "保质期")
    private Long shelfLife;

    @ApiModelProperty("保质期单位")
    @Excel(name = "保质期单位")
    private String shelfLifeUnit;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long unitId;

    @ApiModelProperty("单位名称")
    @Excel(name = "单位名称")
    private String unitName;

    @ApiModelProperty("销售单价")
    @Excel(name = "销售单价")
    private BigDecimal saleUnitPrice;

    @ApiModelProperty("销售单价")
    @Excel(name = "销售单价")
    private BigDecimal purchaseUnitPrice;

    @ApiModelProperty("活动单价")
    @Excel(name = "活动单价")
    private BigDecimal promoUnitPrice;
}
