package com.cyl.wms.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 仓库对象 wm_warehouse
 * 
 * @author zcc
 */
@ApiModel(description="仓库对象")
@Data
@TableName("wm_warehouse")
public class WmWarehouse extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String warehouseNo;

    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String warehouseName;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

}
