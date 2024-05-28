package com.cyl.wms.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmOrderDTO extends BaseAudit {
    private Long id;

    private Integer orderType;
    private Long userType;
    private Long userNo;
    private Long userName;
    private Long warehouseId;
    private Long warehouseName;
    private BigDecimal payableAmount;
    private Integer orderStatus;
    private String remark;
}
