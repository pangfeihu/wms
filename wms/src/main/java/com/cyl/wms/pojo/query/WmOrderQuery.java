package com.cyl.wms.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单 查询 对象")
@Data
public class WmOrderQuery {

    @ApiModelProperty("订单类型 精确匹配")
    private Integer orderType;

    @ApiModelProperty("用户类型 精确匹配")
    private Long userType;

    @ApiModelProperty("用户编号 精确匹配")
    private Long userNo;

    @ApiModelProperty("用户名称 精确匹配")
    private Long userNameLike;

    @ApiModelProperty("仓库编号 精确匹配")
    private Long warehouseId;

    @ApiModelProperty("仓库名称 精确匹配")
    private Long warehouseNameLike;

    @ApiModelProperty("订单金额 精确匹配")
    private BigDecimal payableAmount;

    @ApiModelProperty("订单状态 精确匹配")
    private Integer orderStatus;

}
