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
import com.cyl.wms.convert.WmGoodsUnitConverConvert;
import com.cyl.wms.domain.WmGoodsUnitConver;
import com.cyl.wms.pojo.query.WmGoodsUnitConverQuery;
import com.cyl.wms.service.WmGoodsUnitConverService;
import com.cyl.wms.pojo.vo.WmGoodsUnitConverVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 商品单位转换Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="商品单位转换接口列表")
@RestController
@RequestMapping("/wms/wmGoodsUnitConver")
public class WmGoodsUnitConverController extends BaseController {
    @Autowired
    private WmGoodsUnitConverService service;
    @Autowired
    private WmGoodsUnitConverConvert convert;

    @ApiOperation("查询商品单位转换列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnitConver:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmGoodsUnitConver>> list(@RequestBody WmGoodsUnitConverQuery query, Pageable page) {
        List<WmGoodsUnitConver> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出商品单位转换列表")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnitConver:export')")
    @Log(title = "商品单位转换", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmGoodsUnitConverQuery query) {
        List<WmGoodsUnitConver> list = service.selectList(query, null);
        ExcelUtil<WmGoodsUnitConverVO> util = new ExcelUtil<>(WmGoodsUnitConverVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "商品单位转换数据"));
    }

    @ApiOperation("获取商品单位转换详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnitConver:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmGoodsUnitConver> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增商品单位转换")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnitConver:add')")
    @Log(title = "商品单位转换", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmGoodsUnitConver wmGoodsUnitConver) {
        return ResponseEntity.ok(service.insert(wmGoodsUnitConver));
    }

    @ApiOperation("修改商品单位转换")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnitConver:edit')")
    @Log(title = "商品单位转换", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmGoodsUnitConver wmGoodsUnitConver) {
        return ResponseEntity.ok(service.update(wmGoodsUnitConver));
    }

    @ApiOperation("删除商品单位转换")
    @PreAuthorize("@ss.hasPermi('wms:wmGoodsUnitConver:remove')")
    @Log(title = "商品单位转换", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
