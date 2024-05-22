package com.cyl.wms.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 库存对象 wms_stock
 * 
 * @author zcc
 */
@ApiModel(description="库存对象")
@Data
@TableName("wms_stock")
public class Stock extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("所属仓库")
    @Excel(name = "所属仓库")
    private Long warehouseId;

    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private Long goodsId;

    @ApiModelProperty("生产日期")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prodDate;

    @ApiModelProperty("大包装库存")
    @Excel(name = "大包装库存")
    private BigDecimal maxQuantity;

    @ApiModelProperty("中包装库存")
    @Excel(name = "中包装库存")
    private BigDecimal aveQuantity;

    @ApiModelProperty("小包装库存")
    @Excel(name = "小包装库存")
    private BigDecimal minQuantity;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

}
