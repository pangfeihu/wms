package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存记录 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmInventoryHistoryVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 订单编号 */
    @Excel(name = "订单编号")
    private Long orderId;
   /** 订单类型 */
    @Excel(name = "订单类型")
    private Integer orderType;
   /** 单位编号 */
    @Excel(name = "单位编号")
    private Long unitId;
   /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;
   /** 库存变化 */
    @Excel(name = "库存变化")
    private BigDecimal quantity;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
   /** 所属仓库 */
    @Excel(name = "所属仓库")
    private Long warehouseId;
}
