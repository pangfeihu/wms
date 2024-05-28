package com.cyl.wms.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单位 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="单位 查询 对象")
@Data
public class WmUnitQuery {
    @ApiModelProperty("规格名称 精确匹配")
    private String unitNameLike;

}
