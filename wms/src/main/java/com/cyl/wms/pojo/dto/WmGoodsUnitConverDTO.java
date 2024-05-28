package com.cyl.wms.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 商品单位转换 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmGoodsUnitConverDTO {
    private Long id;
    private Long goodsId;
    private Long maxUnitId;
    private Long minUnitId;
    private Long ratio;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
