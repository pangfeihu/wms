package com.cyl.wms.controller;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
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
import com.cyl.wms.convert.UnitConvert;
import com.cyl.wms.domain.Unit;
import com.cyl.wms.pojo.query.UnitQuery;
import com.cyl.wms.service.UnitService;
import com.cyl.wms.pojo.vo.UnitVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 单位Controller
 *
 * @author zcc
 * @date 2024-05-22
 */
@Api(description ="单位接口列表")
@RestController
@RequestMapping("/wms/unit")
public class UnitController extends BaseController {
    @Autowired
    private UnitService service;
    @Autowired
    private UnitConvert convert;

    @ApiOperation("查询单位列表")
    @PreAuthorize("@ss.hasPermi('wms:unit:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Unit>> list(@RequestBody UnitQuery query, Pageable page) {
        List<Unit> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("查询单位列表")
    @PreAuthorize("@ss.hasPermi('wms:unit:listAll')")
    @GetMapping("/listAll")
    public AjaxResult listALl() {
        List<Unit> list = service.selectList();
        return AjaxResult.success(list);
    }

    @ApiOperation("导出单位列表")
    @PreAuthorize("@ss.hasPermi('wms:unit:export')")
    @Log(title = "单位", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(UnitQuery query) {
        List<Unit> list = service.selectList(query, null);
        ExcelUtil<UnitVO> util = new ExcelUtil<>(UnitVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "单位数据"));
    }

    @ApiOperation("获取单位详细信息")
    @PreAuthorize("@ss.hasPermi('wms:unit:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Unit> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增单位")
    @PreAuthorize("@ss.hasPermi('wms:unit:add')")
    @Log(title = "单位", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Unit unit) {
        return ResponseEntity.ok(service.insert(unit));
    }

    @ApiOperation("修改单位")
    @PreAuthorize("@ss.hasPermi('wms:unit:edit')")
    @Log(title = "单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Unit unit) {
        return ResponseEntity.ok(service.update(unit));
    }

    @ApiOperation("删除单位")
    @PreAuthorize("@ss.hasPermi('wms:unit:remove')")
    @Log(title = "单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
