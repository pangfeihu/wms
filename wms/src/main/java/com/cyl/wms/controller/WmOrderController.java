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
import com.cyl.wms.convert.WmOrderConvert;
import com.cyl.wms.domain.WmOrder;
import com.cyl.wms.pojo.query.WmOrderQuery;
import com.cyl.wms.service.WmOrderService;
import com.cyl.wms.pojo.vo.WmOrderVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单Controller
 *
 * @author zcc
 * @date 2024-05-23
 */
@Api(description ="订单接口列表")
@RestController
@RequestMapping("/wms/wmOrder")
public class WmOrderController extends BaseController {
    @Autowired
    private WmOrderService service;
    @Autowired
    private WmOrderConvert convert;

    @ApiOperation("查询订单列表")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WmOrder>> list(@RequestBody WmOrderQuery query, Pageable page) {
        List<WmOrder> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }


    @ApiOperation("导出订单列表")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WmOrderQuery query) {
        List<WmOrder> list = service.selectList(query, null);
        ExcelUtil<WmOrderVO> util = new ExcelUtil<>(WmOrderVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单数据"));
    }

    @ApiOperation("获取订单详细信息")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WmOrder> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WmOrder wmOrder) {
        // 获取当前用户
        wmOrder.setCreateBy(getUserId());
        return ResponseEntity.ok(service.insert(wmOrder));
    }

    @ApiOperation("订单完成")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:complete')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PutMapping("/complete/{ids}")
    public ResponseEntity<Integer> complete(@PathVariable Long[] ids) {
        // 获取当前用户
        Long userId = getUserId();
        // 业务处理
        return ResponseEntity.ok(service.complete(ids,userId));
    }

    @ApiOperation("修改订单")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WmOrder wmOrder) {
        return ResponseEntity.ok(service.update(wmOrder));
    }

    @ApiOperation("删除订单")
    @PreAuthorize("@ss.hasPermi('wms:wmOrder:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }

}
