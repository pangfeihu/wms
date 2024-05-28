package com.cyl.wms.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 规格 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="规格 查询 对象")
@Data
public class WmSpecQuery {
    @ApiModelProperty("规格名称 精确匹配")
    private String specNameLike;

    @ApiModelProperty("规格描述 精确匹配")
    private String specDesc;

}
