package com.cyl.wms.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 库存记录对象 wm_inventory_history
 * 
 * @author zcc
 */
@ApiModel(description="库存记录对象")
@Data
@TableName("wm_inventory_history")
public class WmInventoryHistory extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private Long orderId;

    @ApiModelProperty("订单类型")
    @Excel(name = "订单类型")
    private Integer orderType;

    @ApiModelProperty("单位编号")
    @Excel(name = "单位编号")
    private Long unitId;

    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private Long goodsId;

    @ApiModelProperty("库存变化")
    @Excel(name = "库存变化")
    private BigDecimal quantity;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("所属仓库")
    @Excel(name = "所属仓库")
    private Long warehouseId;

}
