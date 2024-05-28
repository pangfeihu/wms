package com.cyl.wms.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 单位 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmUnitDTO {
    private Long id;
    private String unitName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
