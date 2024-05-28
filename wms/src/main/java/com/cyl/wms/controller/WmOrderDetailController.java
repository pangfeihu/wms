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
import com.cyl.wms.convert.WmOrderDetailConvert;
import com.cyl.wms.domain.WmOrderDetail;
import com.cyl.wms.pojo.query.WmOrderDetailQuery;
import com.cyl.wms.service.WmOrderDetailService;
import com.cyl.wms.pojo.vo.WmOrderDetailVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单明细Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="订单明细接口列表")
@RestController
@RequestMapping("/wms/wmOrderDetail")
public class WmOrderDetailController extends BaseController {
    @Autowired
    private WmOrderDetailService service;
    @Autowired
    private WmOrderDetailConvert convert;

    @ApiOperation("查询订单明细列表")
    @PreAuthorize("@ss.hasPermi('wms:wmOrderDetail:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmOrderDetail>> list(@RequestBody WmOrderDetailQuery query, Pageable page) {
        List<WmOrderDetail> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单明细列表")
    @PreAuthorize("@ss.hasPermi('wms:wmOrderDetail:export')")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmOrderDetailQuery query) {
        List<WmOrderDetail> list = service.selectList(query, null);
        ExcelUtil<WmOrderDetailVO> util = new ExcelUtil<>(WmOrderDetailVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单明细数据"));
    }

    @ApiOperation("获取订单明细详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmOrderDetail:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmOrderDetail> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单明细")
    @PreAuthorize("@ss.hasPermi('wms:wmOrderDetail:add')")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmOrderDetail wmOrderDetail) {
        return ResponseEntity.ok(service.insert(wmOrderDetail));
    }

    @ApiOperation("修改订单明细")
    @PreAuthorize("@ss.hasPermi('wms:wmOrderDetail:edit')")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmOrderDetail wmOrderDetail) {
        return ResponseEntity.ok(service.update(wmOrderDetail));
    }

    @ApiOperation("删除订单明细")
    @PreAuthorize("@ss.hasPermi('wms:wmOrderDetail:remove')")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
