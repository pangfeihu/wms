package com.cyl.wms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 商品表对象 wm_goods
 * 
 * @author zcc
 */
@ApiModel(description="商品表对象")
@Data
@TableName("wm_goods")
public class WmGoods extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String goodsNo;

    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String goodsName;

    @ApiModelProperty("供应商编号")
    @Excel(name = "供应商编号")
    private Long supplierId;

    @ApiModelProperty("分类")
    @Excel(name = "分类")
    private Long goodsTypeId;

    @ApiModelProperty("保质期")
    @Excel(name = "保质期")
    private Long shelfLife;

    @ApiModelProperty("保质期单位")
    @Excel(name = "保质期单位")
    private String shelfLifeUnit;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("商品单位集合")
    @TableField(exist = false)
    private List<WmGoodsUnit> details;

}
