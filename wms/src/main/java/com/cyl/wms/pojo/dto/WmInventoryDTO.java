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
public class WmInventoryDTO extends BaseAudit {
    private Long id;
    private Long goodsId;
    private Long unitId;
    private BigDecimal quantity;
    private String remark;
    private Long warehouseId;
}
