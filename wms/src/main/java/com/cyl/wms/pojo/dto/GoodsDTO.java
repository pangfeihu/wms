package com.cyl.wms.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 商品表 DTO 对象
 *
 * @author zcc
 */
@Data
public class GoodsDTO extends BaseAudit {
    private Long id;
    private String goodsNo;
    private String goodsName;
    private Long brandNo;
    private String goodsSpec;
    private String goodsType;
    private String maxUnit;
    private BigDecimal maxUnitPrice;
    private String aveUnit;
    private BigDecimal aveUnitPrice;
    private String remark;
    private String minUnit;
    private BigDecimal minUnitPrice;
    private Long maxAveNum;
    private Long maxMinNum;
    private Long aveMinNum;
    private Long shelfLife;
    private String shelfLifeUnit;
}
