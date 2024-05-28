package com.cyl.wms.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 库存对象 wm_inventory
 * 
 * @author zcc
 */
@ApiModel(description="库存对象")
@Data
@TableName("wm_inventory")
public class WmInventory extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    @Excel(name = "商品名称")
    @TableField(exist = false)
    private String goodsName;

    @ApiModelProperty("单位编号")
    @Excel(name = "单位编号")
    private Long unitId;

    @ApiModelProperty("单位名称")
    @Excel(name = "单位名称")
    @TableField(exist = false)
    private String unitName;

    @ApiModelProperty("规格名称")
    @Excel(name = "规格名称")
    @TableField(exist = false)
    private String specName;

    @ApiModelProperty("库存")
    @Excel(name = "库存")
    private Long quantity;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("所属仓库")
    @Excel(name = "所属仓库")
    private Long warehouseId;

    /** 销售单价 */
    @Excel(name = "销售单价")
    @TableField(exist = false)
    private BigDecimal saleUnitPrice;

    /** 销售单价 */
    @Excel(name = "销售单价")
    @TableField(exist = false)
    private BigDecimal purchaseUnitPrice;

    /** 活动单价 */
    @Excel(name = "活动单价")
    @TableField(exist = false)
    private BigDecimal promoUnitPrice;

}
