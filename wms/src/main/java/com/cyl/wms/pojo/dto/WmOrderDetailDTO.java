package com.cyl.wms.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单明细 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmOrderDetailDTO extends BaseAudit {
    private Long id;
    private Long orderId;
    private Long goodsId;
    private BigDecimal planQuantity;
    private BigDecimal realQuantity;
    private Long unitId;
    private Long type;
    private BigDecimal money;
    private String remark;
}
