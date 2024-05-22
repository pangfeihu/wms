package com.cyl.wms.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 单位对象 wms_unit
 * 
 * @author zcc
 */
@ApiModel(description="单位对象")
@Data
@TableName("wms_unit")
public class Unit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @Excel(name = "ID")
    private Long id;

    @ApiModelProperty("规格名称")
    @Excel(name = "规格名称")
    private String unitName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标识")
    private Integer delFlag;


}
