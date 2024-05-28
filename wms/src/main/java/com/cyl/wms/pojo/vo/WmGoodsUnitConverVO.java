package com.cyl.wms.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 商品单位转换 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WmGoodsUnitConverVO  {
   /** ID */
    private Long id;
   /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;
   /** 大包装单位id */
    @Excel(name = "大包装单位id")
    private Long maxUnitId;
   /** 小包装单位id */
    @Excel(name = "小包装单位id")
    private Long minUnitId;
   /** 比例 */
    @Excel(name = "比例")
    private Long ratio;
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 修改时间 */
    private LocalDateTime updateTime;
}
