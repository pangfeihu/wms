package com.cyl.wms.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cyl.wms.convert.StockConvert;
import com.cyl.wms.domain.Stock;
import com.cyl.wms.pojo.query.StockQuery;
import com.cyl.wms.service.StockService;
import com.cyl.wms.pojo.vo.StockVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 库存Controller
 *
 * @author zcc
 * @date 2024-05-22
 */
@Api(description ="库存接口列表")
@RestController
@RequestMapping("/wms/stock")
public class StockController extends BaseController {
    @Autowired
    private StockService service;
    @Autowired
    private StockConvert convert;

    @ApiOperation("查询库存列表")
    @PreAuthorize("@ss.hasPermi('wms:stock:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Stock>> list(@RequestBody StockQuery query, Pageable page) {
        List<Stock> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出库存列表")
    @PreAuthorize("@ss.hasPermi('wms:stock:export')")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(StockQuery query) {
        List<Stock> list = service.selectList(query, null);
        ExcelUtil<StockVO> util = new ExcelUtil<>(StockVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "库存数据"));
    }

    @ApiOperation("获取库存详细信息")
    @PreAuthorize("@ss.hasPermi('wms:stock:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

}
