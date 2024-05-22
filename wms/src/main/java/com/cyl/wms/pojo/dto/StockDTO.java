package com.cyl.wms.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存 DTO 对象
 *
 * @author zcc
 */
@Data
public class StockDTO extends BaseAudit {
    private Long id;
    private Long warehouseId;
    private Long goodsId;
    private LocalDateTime prodDate;
    private BigDecimal maxQuantity;
    private BigDecimal aveQuantity;
    private BigDecimal minQuantity;
    private String remark;
}
