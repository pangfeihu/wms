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
import com.cyl.wms.convert.WmGoodsConvert;
import com.cyl.wms.domain.WmGoods;
import com.cyl.wms.pojo.query.WmGoodsQuery;
import com.cyl.wms.service.WmGoodsService;
import com.cyl.wms.pojo.vo.WmGoodsVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 商品表Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="商品表接口列表")
@RestController
@RequestMapping("/wms/wmGoods")
public class WmGoodsController extends BaseController {
    @Autowired
    private WmGoodsService service;
    @Autowired
    private WmGoodsConvert convert;

    @ApiOperation("查询商品表列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmGoods>> list(@RequestBody WmGoodsQuery query, Pageable page) {
        List<WmGoods> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("查询商品表列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:listDetail')")
    @PostMapping("/listDetail")
    public ResponseEntity<Page<WmGoodsVO>> listDetail(@RequestBody WmGoodsQuery query, Pageable page) {
        List<WmGoodsVO> list = service.selectListDetail(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出商品表列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:export')")
    @Log(title = "商品表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmGoodsQuery query) {
        List<WmGoods> list = service.selectList(query, null);
        ExcelUtil<WmGoodsVO> util = new ExcelUtil<>(WmGoodsVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "商品表数据"));
    }

    @ApiOperation("获取商品表详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmGoods> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增商品表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:add')")
    @Log(title = "商品表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmGoods wmGoods) {
        return ResponseEntity.ok(service.insert(wmGoods));
    }

    @ApiOperation("修改商品表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:edit')")
    @Log(title = "商品表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmGoods wmGoods) {
        return ResponseEntity.ok(service.update(wmGoods));
    }

    @ApiOperation("删除商品表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoods:remove')")
    @Log(title = "商品表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
