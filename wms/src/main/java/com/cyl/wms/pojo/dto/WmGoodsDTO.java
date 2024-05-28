package com.cyl.wms.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 商品表 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmGoodsDTO extends BaseAudit {
    private Long id;
    private String goodsNo;
    private String goodsName;
    private Long supplierId;
    private Long goodsSpecId;
    private Long goodsTypeId;
    private Long shelfLife;
    private String shelfLifeUnit;
    private String remark;
}
