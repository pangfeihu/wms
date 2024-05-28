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
import com.cyl.wms.convert.WmInventoryConvert;
import com.cyl.wms.domain.WmInventory;
import com.cyl.wms.pojo.query.WmInventoryQuery;
import com.cyl.wms.service.WmInventoryService;
import com.cyl.wms.pojo.vo.WmInventoryVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 库存Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="库存接口列表")
@RestController
@RequestMapping("/wms/wmInventory")
public class WmInventoryController extends BaseController {
    @Autowired
    private WmInventoryService service;
    @Autowired
    private WmInventoryConvert convert;

    @ApiOperation("查询库存列表")
    @PreAuthorize("@ss.hasPermi('wms:wmInventory:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmInventory>> list(@RequestBody WmInventoryQuery query, Pageable page) {
        List<WmInventory> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出库存列表")
    @PreAuthorize("@ss.hasPermi('wms:wmInventory:export')")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmInventoryQuery query) {
        List<WmInventory> list = service.selectList(query, null);
        ExcelUtil<WmInventoryVO> util = new ExcelUtil<>(WmInventoryVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "库存数据"));
    }

    @ApiOperation("获取库存详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmInventory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmInventory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增库存")
    @PreAuthorize("@ss.hasPermi('wms:wmInventory:add')")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmInventory wmInventory) {
        return ResponseEntity.ok(service.insert(wmInventory));
    }

    @ApiOperation("修改库存")
    @PreAuthorize("@ss.hasPermi('wms:wmInventory:edit')")
    @Log(title = "库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmInventory wmInventory) {
        return ResponseEntity.ok(service.update(wmInventory));
    }

    @ApiOperation("删除库存")
    @PreAuthorize("@ss.hasPermi('wms:wmInventory:remove')")
    @Log(title = "库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
