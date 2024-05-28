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
import com.cyl.wms.convert.WmInventoryHistoryConvert;
import com.cyl.wms.domain.WmInventoryHistory;
import com.cyl.wms.pojo.query.WmInventoryHistoryQuery;
import com.cyl.wms.service.WmInventoryHistoryService;
import com.cyl.wms.pojo.vo.WmInventoryHistoryVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 库存记录Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="库存记录接口列表")
@RestController
@RequestMapping("/wms/wmInventoryHistory")
public class WmInventoryHistoryController extends BaseController {
    @Autowired
    private WmInventoryHistoryService service;
    @Autowired
    private WmInventoryHistoryConvert convert;

    @ApiOperation("查询库存记录列表")
    @PreAuthorize("@ss.hasPermi('wms:wmInventoryHistory:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmInventoryHistory>> list(@RequestBody WmInventoryHistoryQuery query, Pageable page) {
        List<WmInventoryHistory> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出库存记录列表")
    @PreAuthorize("@ss.hasPermi('wms:wmInventoryHistory:export')")
    @Log(title = "库存记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmInventoryHistoryQuery query) {
        List<WmInventoryHistory> list = service.selectList(query, null);
        ExcelUtil<WmInventoryHistoryVO> util = new ExcelUtil<>(WmInventoryHistoryVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "库存记录数据"));
    }

    @ApiOperation("获取库存记录详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmInventoryHistory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmInventoryHistory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增库存记录")
    @PreAuthorize("@ss.hasPermi('wms:wmInventoryHistory:add')")
    @Log(title = "库存记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmInventoryHistory wmInventoryHistory) {
        return ResponseEntity.ok(service.insert(wmInventoryHistory));
    }

    @ApiOperation("修改库存记录")
    @PreAuthorize("@ss.hasPermi('wms:wmInventoryHistory:edit')")
    @Log(title = "库存记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmInventoryHistory wmInventoryHistory) {
        return ResponseEntity.ok(service.update(wmInventoryHistory));
    }

    @ApiOperation("删除库存记录")
    @PreAuthorize("@ss.hasPermi('wms:wmInventoryHistory:remove')")
    @Log(title = "库存记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
