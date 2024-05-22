package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存 数据视图对象
 * 
 * @author zcc
 */
@Data
public class StockVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 所属仓库 */
    @Excel(name = "所属仓库")
    private Long warehouseId;
   /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;
   /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prodDate;
   /** 大包装库存 */
    @Excel(name = "大包装库存")
    private BigDecimal maxQuantity;
   /** 中包装库存 */
    @Excel(name = "中包装库存")
    private BigDecimal aveQuantity;
   /** 小包装库存 */
    @Excel(name = "小包装库存")
    private BigDecimal minQuantity;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
