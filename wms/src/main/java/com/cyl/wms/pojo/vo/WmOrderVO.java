package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmOrderVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 订单类型 */
    @Excel(name = "订单类型")
    private Integer orderType;
   /** 用户类型 */
    @Excel(name = "用户类型")
    private Long userType;
   /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userNo;
   /** 用户名称 */
    @Excel(name = "用户名称")
    private Long userName;
   /** 仓库编号 */
    @Excel(name = "仓库编号")
    private Long warehouseId;
   /** 仓库名称 */
    @Excel(name = "仓库名称")
    private Long warehouseName;
   /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal payableAmount;
   /** 订单状态 */
    @Excel(name = "订单状态")
    private Integer orderStatus;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
