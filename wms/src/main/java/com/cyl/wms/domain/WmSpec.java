package com.cyl.wms.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 规格对象 wm_spec
 * 
 * @author zcc
 */
@ApiModel(description="规格对象")
@Data
@TableName("wm_spec")
public class WmSpec {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("规格名称")
    @Excel(name = "规格名称")
    private String specName;

    @ApiModelProperty("规格描述")
    @Excel(name = "规格描述")
    private String specDesc;

    @ApiModelProperty("删除表示")
    private Long delFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
