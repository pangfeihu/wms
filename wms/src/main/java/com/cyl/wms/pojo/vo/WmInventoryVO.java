package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmInventoryVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;
   /** 单位编号 */
    @Excel(name = "单位编号")
    private Long unitId;
   /** 库存 */
    @Excel(name = "库存")
    private BigDecimal quantity;
   /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prodDate;
   /** 过期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "过期日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expDate;
   /** 临期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "临期日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime advDate;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
   /** 所属仓库 */
    @Excel(name = "所属仓库")
    private Long warehouseId;
   /** 所属库区 */
    @Excel(name = "所属库区")
    private Long areaId;
}
