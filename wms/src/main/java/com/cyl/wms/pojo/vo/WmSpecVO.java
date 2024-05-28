package com.cyl.wms.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 规格 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmSpecVO  {
   /** ID */
    private Long id;
   /** 规格名称 */
    @Excel(name = "规格名称")
    private String specName;
   /** 规格描述 */
    @Excel(name = "规格描述")
    private String specDesc;
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 修改时间 */
    private LocalDateTime updateTime;
}
