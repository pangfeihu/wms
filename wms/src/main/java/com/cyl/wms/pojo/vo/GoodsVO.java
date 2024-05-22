package com.cyl.wms.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 商品表 数据视图对象
 * 
 * @author zcc
 */
@Data
public class GoodsVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 编号 */
    @Excel(name = "编号")
    private String goodsNo;
   /** 名称 */
    @Excel(name = "名称")
    private String goodsName;
   /** 品牌 */
    @Excel(name = "品牌")
    private Long brandNo;
   /** 规格 */
    @Excel(name = "规格")
    private String goodsSpec;
   /** 分类 */
    @Excel(name = "分类")
    private String goodsType;
   /** 大包装 */
    @Excel(name = "大包装")
    private String maxUnit;
   /** 大包装单价 */
    @Excel(name = "大包装单价")
    private BigDecimal maxUnitPrice;
   /** 中包装 */
    @Excel(name = "中包装")
    private String aveUnit;
   /** 中包装单价 */
    @Excel(name = "中包装单价")
    private BigDecimal aveUnitPrice;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
   /** 小包装 */
    @Excel(name = "小包装")
    private String minUnit;
   /** 小包装单价 */
    @Excel(name = "小包装单价")
    private BigDecimal minUnitPrice;
   /** 大包装包含多少中包装 */
    @Excel(name = "大包装包含多少中包装")
    private Long maxAveNum;
   /** 大包装包含多少小包装 */
    @Excel(name = "大包装包含多少小包装")
    private Long maxMinNum;
   /** 中包装包含多少小包装 */
    @Excel(name = "中包装包含多少小包装")
    private Long aveMinNum;
   /** 保质期 */
    @Excel(name = "保质期")
    private Long shelfLife;
   /** 保质期单位 */
    @Excel(name = "保质期单位")
    private String shelfLifeUnit;
}
