package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 商品单位表 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmGoodsUnitVO  {
   /** ID */
    private Long id;
   /** 单位id */
    @Excel(name = "单位id")
    private Long unitId;
   /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;
   /** 销售单价 */
    @Excel(name = "销售单价")
    private BigDecimal saleUnitPrice;
   /** 销售单价 */
    @Excel(name = "销售单价")
    private BigDecimal purchaseUnitPrice;
   /** 活动单价 */
    @Excel(name = "活动单价")
    private BigDecimal promoUnitPrice;
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 修改时间 */
    private LocalDateTime updateTime;
}
