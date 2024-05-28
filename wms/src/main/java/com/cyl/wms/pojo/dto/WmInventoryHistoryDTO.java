package com.cyl.wms.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存记录 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmInventoryHistoryDTO extends BaseAudit {
    private Long id;
    private Long orderId;
    private Integer orderType;
    private Long unitId;
    private Long goodsId;
    private BigDecimal quantity;
    private String remark;
    private Long warehouseId;
}
