package com.cyl.wms.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商品单位转换对象 wm_goods_unit_conver
 * 
 * @author zcc
 */
@ApiModel(description="商品单位转换对象")
@Data
@TableName("wm_goods_unit_conver")
public class WmGoodsUnitConver {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("商品id")
    @Excel(name = "商品id")
    private Long goodsId;

    @ApiModelProperty("大包装单位id")
    @Excel(name = "大包装单位id")
    private Long maxUnitId;

    @ApiModelProperty("小包装单位id")
    @Excel(name = "小包装单位id")
    private Long minUnitId;

    @ApiModelProperty("比例")
    @Excel(name = "比例")
    private Long ratio;

    @ApiModelProperty("删除标识")
    private Long delFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
