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
import com.cyl.wms.convert.GoodsConvert;
import com.cyl.wms.domain.Goods;
import com.cyl.wms.pojo.query.GoodsQuery;
import com.cyl.wms.service.GoodsService;
import com.cyl.wms.pojo.vo.GoodsVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 商品表Controller
 *
 * @author zcc
 * @date 2024-05-22
 */
@Api(description ="商品表接口列表")
@RestController
@RequestMapping("/wms/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService service;
    @Autowired
    private GoodsConvert convert;

    @ApiOperation("查询商品表列表")
    @PreAuthorize("@ss.hasPermi('wms:goods:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Goods>> list(@RequestBody GoodsQuery query, Pageable page) {
        List<Goods> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出商品表列表")
    @PreAuthorize("@ss.hasPermi('wms:goods:export')")
    @Log(title = "商品表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(GoodsQuery query) {
        List<Goods> list = service.selectList(query, null);
        ExcelUtil<GoodsVO> util = new ExcelUtil<>(GoodsVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "商品表数据"));
    }

    @ApiOperation("获取商品表详细信息")
    @PreAuthorize("@ss.hasPermi('wms:goods:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Goods> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增商品表")
    @PreAuthorize("@ss.hasPermi('wms:goods:add')")
    @Log(title = "商品表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Goods goods) {
        return ResponseEntity.ok(service.insert(goods));
    }

    @ApiOperation("修改商品表")
    @PreAuthorize("@ss.hasPermi('wms:goods:edit')")
    @Log(title = "商品表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Goods goods) {
        return ResponseEntity.ok(service.update(goods));
    }

    @ApiOperation("删除商品表")
    @PreAuthorize("@ss.hasPermi('wms:goods:remove')")
    @Log(title = "商品表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
