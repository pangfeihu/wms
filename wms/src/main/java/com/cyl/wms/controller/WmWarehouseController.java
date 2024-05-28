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
import com.cyl.wms.convert.WmWarehouseConvert;
import com.cyl.wms.domain.WmWarehouse;
import com.cyl.wms.pojo.query.WmWarehouseQuery;
import com.cyl.wms.service.WmWarehouseService;
import com.cyl.wms.pojo.vo.WmWarehouseVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 仓库Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="仓库接口列表")
@RestController
@RequestMapping("/wms/wmWarehouse")
public class WmWarehouseController extends BaseController {
    @Autowired
    private WmWarehouseService service;
    @Autowired
    private WmWarehouseConvert convert;

    @ApiOperation("查询仓库列表")
    @PreAuthorize("@ss.hasPermi('wms:wmWarehouse:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmWarehouse>> list(@RequestBody WmWarehouseQuery query, Pageable page) {
        List<WmWarehouse> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出仓库列表")
    @PreAuthorize("@ss.hasPermi('wms:wmWarehouse:export')")
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmWarehouseQuery query) {
        List<WmWarehouse> list = service.selectList(query, null);
        ExcelUtil<WmWarehouseVO> util = new ExcelUtil<>(WmWarehouseVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "仓库数据"));
    }

    @ApiOperation("获取仓库详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmWarehouse:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmWarehouse> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增仓库")
    @PreAuthorize("@ss.hasPermi('wms:wmWarehouse:add')")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmWarehouse wmWarehouse) {
        return ResponseEntity.ok(service.insert(wmWarehouse));
    }

    @ApiOperation("修改仓库")
    @PreAuthorize("@ss.hasPermi('wms:wmWarehouse:edit')")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmWarehouse wmWarehouse) {
        return ResponseEntity.ok(service.update(wmWarehouse));
    }

    @ApiOperation("删除仓库")
    @PreAuthorize("@ss.hasPermi('wms:wmWarehouse:remove')")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
