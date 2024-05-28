package com.cyl.wms.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 单位对象 wm_unit
 * 
 * @author zcc
 */
@ApiModel(description="单位对象")
@Data
@TableName("wm_unit")
public class WmUnit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("包装名称")
    @Excel(name = "包装名称")
    private String unitName;

    @ApiModelProperty("删除表示")
    private Long delFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
