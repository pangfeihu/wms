package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单明细 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmOrderDetailVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 订单编号 */
    @Excel(name = "订单编号")
    private Long orderId;
   /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;
   /** 计划数量 */
    @Excel(name = "计划数量")
    private BigDecimal planQuantity;
   /** 实际数量 */
    @Excel(name = "实际数量")
    private BigDecimal realQuantity;
   /** 单位 */
    @Excel(name = "单位")
    private Long unit;
   /** 类型 */
    @Excel(name = "类型")
    private Long type;
   /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
