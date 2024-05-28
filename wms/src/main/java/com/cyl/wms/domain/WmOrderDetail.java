package com.cyl.wms.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单明细对象 wm_order_detail
 * 
 * @author zcc
 */
@ApiModel(description="订单明细对象")
@Data
@TableName("wm_order_detail")
public class WmOrderDetail extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private Long orderId;

    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private Long goodsId;

    @ApiModelProperty("实际数量")
    @Excel(name = "实际数量")
    private Long realQuantity;

    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private Long unitId;

    @ApiModelProperty("类型")
    @Excel(name = "类型")
    private Long type;

    @ApiModelProperty("金额")
    @Excel(name = "金额")
    private BigDecimal money;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

}
