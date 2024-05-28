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
import com.cyl.wms.convert.WmGoodsUnitConvert;
import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.pojo.query.WmGoodsUnitQuery;
import com.cyl.wms.service.WmGoodsUnitService;
import com.cyl.wms.pojo.vo.WmGoodsUnitVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 商品单位表Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="商品单位表接口列表")
@RestController
@RequestMapping("/wms/wmGoodsUnit")
public class WmGoodsUnitController extends BaseController {
    @Autowired
    private WmGoodsUnitService service;
    @Autowired
    private WmGoodsUnitConvert convert;

    @ApiOperation("查询商品单位表列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnit:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmGoodsUnit>> list(@RequestBody WmGoodsUnitQuery query, Pageable page) {
        List<WmGoodsUnit> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出商品单位表列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnit:export')")
    @Log(title = "商品单位表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmGoodsUnitQuery query) {
        List<WmGoodsUnit> list = service.selectList(query, null);
        ExcelUtil<WmGoodsUnitVO> util = new ExcelUtil<>(WmGoodsUnitVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "商品单位表数据"));
    }

    @ApiOperation("获取商品单位表详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnit:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmGoodsUnit> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增商品单位表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnit:add')")
    @Log(title = "商品单位表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmGoodsUnit wmGoodsUnit) {
        return ResponseEntity.ok(service.insert(wmGoodsUnit));
    }

    @ApiOperation("修改商品单位表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnit:edit')")
    @Log(title = "商品单位表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmGoodsUnit wmGoodsUnit) {
        return ResponseEntity.ok(service.update(wmGoodsUnit));
    }

    @ApiOperation("删除商品单位表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnit:remove')")
    @Log(title = "商品单位表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
