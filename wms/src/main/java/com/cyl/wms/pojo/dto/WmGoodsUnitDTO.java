package com.cyl.wms.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * 商品单位表 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmGoodsUnitDTO {
    private Long id;
    private Long unitId;
    private Long goodsId;
    private BigDecimal saleUnitPrice;
    private BigDecimal purchaseUnitPrice;
    private BigDecimal promoUnitPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
