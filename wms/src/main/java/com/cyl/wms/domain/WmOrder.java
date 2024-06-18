package com.cyl.wms.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单对象 wm_order
 * 
 * @author zcc
 */
@ApiModel(description="订单对象")
@Data
@TableName("wm_order")
public class WmOrder extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单类型")
    @Excel(name = "订单类型")
    private Integer orderType;

    @ApiModelProperty("用户类型")
    @Excel(name = "用户类型")
    private Long userType;

    @ApiModelProperty("用户编号")
    @Excel(name = "用户编号")
    private Long userId;

    @ApiModelProperty("用户名称")
    @Excel(name = "用户名称")
    private String userName;

    @ApiModelProperty("仓库编号")
    @Excel(name = "仓库编号")
    private Long warehouseId;

    @ApiModelProperty("仓库名称")
    @Excel(name = "仓库名称")
    private String warehouseName;

    @ApiModelProperty("订单金额")
    @Excel(name = "订单金额")
    private BigDecimal payableAmount;

    @ApiModelProperty("订单状态")
    @Excel(name = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty("订单状态描述")
    @Excel(name = "订单状态描述")
    private String orderStatusDesc;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("创建人名称")
    @Excel(name = "创建人名称")
    private String createName;

    @ApiModelProperty("订单明细")
    @TableField(exist = false)
    private List<WmOrderDetail> details;
}
