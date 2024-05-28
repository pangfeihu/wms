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
import com.cyl.wms.convert.WmSpecConvert;
import com.cyl.wms.domain.WmSpec;
import com.cyl.wms.pojo.query.WmSpecQuery;
import com.cyl.wms.service.WmSpecService;
import com.cyl.wms.pojo.vo.WmSpecVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 规格Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="规格接口列表")
@RestController
@RequestMapping("/wms/wmSpec")
public class WmSpecController extends BaseController {
    @Autowired
    private WmSpecService service;
    @Autowired
    private WmSpecConvert convert;

    @ApiOperation("查询规格列表")
    @PreAuthorize("@ss.hasPermi('wms:wmSpec:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmSpec>> list(@RequestBody WmSpecQuery query, Pageable page) {
        List<WmSpec> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出规格列表")
    @PreAuthorize("@ss.hasPermi('wms:wmSpec:export')")
    @Log(title = "规格", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmSpecQuery query) {
        List<WmSpec> list = service.selectList(query, null);
        ExcelUtil<WmSpecVO> util = new ExcelUtil<>(WmSpecVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "规格数据"));
    }

    @ApiOperation("获取规格详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmSpec:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmSpec> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增规格")
    @PreAuthorize("@ss.hasPermi('wms:wmSpec:add')")
    @Log(title = "规格", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmSpec wmSpec) {
        return ResponseEntity.ok(service.insert(wmSpec));
    }

    @ApiOperation("修改规格")
    @PreAuthorize("@ss.hasPermi('wms:wmSpec:edit')")
    @Log(title = "规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmSpec wmSpec) {
        return ResponseEntity.ok(service.update(wmSpec));
    }

    @ApiOperation("删除规格")
    @PreAuthorize("@ss.hasPermi('wms:wmSpec:remove')")
    @Log(title = "规格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
