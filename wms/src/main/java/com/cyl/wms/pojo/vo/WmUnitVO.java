package com.cyl.wms.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 单位 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmUnitVO  {
   /** ID */
    private Long id;
   /** 规格名称 */
    @Excel(name = "规格名称")
    private String unitName;
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 修改时间 */
    private LocalDateTime updateTime;
}
