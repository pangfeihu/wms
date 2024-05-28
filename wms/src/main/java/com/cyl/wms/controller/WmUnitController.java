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
import com.cyl.wms.convert.WmUnitConvert;
import com.cyl.wms.domain.WmUnit;
import com.cyl.wms.pojo.query.WmUnitQuery;
import com.cyl.wms.service.WmUnitService;
import com.cyl.wms.pojo.vo.WmUnitVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 单位Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="单位接口列表")
@RestController
@RequestMapping("/wms/wmUnit")
public class WmUnitController extends BaseController {
    @Autowired
    private WmUnitService service;
    @Autowired
    private WmUnitConvert convert;

    @ApiOperation("查询单位列表")
    @PreAuthorize("@ss.hasPermi('wms:wmUnit:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmUnit>> list(@RequestBody WmUnitQuery query, Pageable page) {
        List<WmUnit> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出单位列表")
    @PreAuthorize("@ss.hasPermi('wms:wmUnit:export')")
    @Log(title = "单位", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmUnitQuery query) {
        List<WmUnit> list = service.selectList(query, null);
        ExcelUtil<WmUnitVO> util = new ExcelUtil<>(WmUnitVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "单位数据"));
    }

    @ApiOperation("获取单位详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmUnit:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmUnit> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增单位")
    @PreAuthorize("@ss.hasPermi('wms:wmUnit:add')")
    @Log(title = "单位", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmUnit wmUnit) {
        return ResponseEntity.ok(service.insert(wmUnit));
    }

    @ApiOperation("修改单位")
    @PreAuthorize("@ss.hasPermi('wms:wmUnit:edit')")
    @Log(title = "单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmUnit wmUnit) {
        return ResponseEntity.ok(service.update(wmUnit));
    }

    @ApiOperation("删除单位")
    @PreAuthorize("@ss.hasPermi('wms:wmUnit:remove')")
    @Log(title = "单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
