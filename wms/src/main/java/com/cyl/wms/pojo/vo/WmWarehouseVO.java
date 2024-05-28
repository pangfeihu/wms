package com.cyl.wms.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 仓库 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmWarehouseVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 编号 */
    @Excel(name = "编号")
    private String warehouseNo;
   /** 名称 */
    @Excel(name = "名称")
    private String warehouseName;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
