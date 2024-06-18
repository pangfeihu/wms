package com.cyl.wms.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商品单位表对象 wm_goods_unit
 * 
 * @author zcc
 */
@ApiModel(description="商品单位表对象")
@Data
@TableName("wm_goods_detail")
public class WmGoodsUnit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long unitId;

    @ApiModelProperty("单位名称")
    @Excel(name = "单位名称")
    @TableField(exist = false)
    private String unitName;

    @ApiModelProperty("商品id")
    @Excel(name = "商品id")
    private Long goodsId;

    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private Long specId;

    @ApiModelProperty("规格名称")
    @Excel(name = "规格")
    private String specName;

    @ApiModelProperty("销售单价")
    @Excel(name = "销售单价")
    private BigDecimal saleUnitPrice;

    @ApiModelProperty("销售单价")
    @Excel(name = "销售单价")
    private BigDecimal purchaseUnitPrice;

    @ApiModelProperty("活动单价")
    @Excel(name = "活动单价")
    private BigDecimal promoUnitPrice;

    @ApiModelProperty("删除标识")
    private Long delFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
