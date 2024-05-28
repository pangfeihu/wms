package com.cyl.wms.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 规格 DTO 对象
 *
 * @author zcc
 */
@Data
public class WmSpecDTO {
    private Long id;
    private String specName;
    private String specDesc;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
