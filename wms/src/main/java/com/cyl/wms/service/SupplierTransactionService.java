package com.cyl.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyl.wms.domain.entity.Supplier;
import com.cyl.wms.domain.entity.SupplierTransaction;
import com.cyl.wms.enums.Transaction;
import com.cyl.wms.mapper.SupplierTransactionMapper;
import com.cyl.wms.domain.query.SupplierTransactionQuery;
import com.cyl.wms.utils.TransAmountUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 供应商账户流水Service业务层处理
 *
 * @author zcc
 */
@Service
public class SupplierTransactionService {
    @Autowired
    private SupplierTransactionMapper supplierTransactionMapper;

    @Autowired
    private SupplierService supplierService;

    /**
     * 查询供应商账户流水
     *
     * @param id 供应商账户流水主键
     * @return 供应商账户流水
     */
    public SupplierTransaction selectById(Integer id) {
        return supplierTransactionMapper.selectById(id);
    }

    /**
     * 查询供应商账户流水列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 供应商账户流水
     */
    public List<SupplierTransaction> selectList(SupplierTransactionQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), "create_time desc");
        }
        LambdaQueryWrapper<SupplierTransaction> qw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getSupplierId())) {
            qw.eq(SupplierTransaction::getSupplierId, query.getSupplierId());
        }
        if (!StringUtils.isEmpty(query.getTransactionCode())) {
            qw.eq(SupplierTransaction::getTransactionCode, query.getTransactionCode());
        }
        if (!StringUtils.isEmpty(query.getTransactionType())) {
            qw.eq(SupplierTransaction::getTransactionType, query.getTransactionType());
        }
        Optional.ofNullable(query.getStartTime()).ifPresent(
                startTime -> qw.ge(SupplierTransaction::getCreateTime, query.getStartTime())
        );
        Optional.ofNullable(query.getEndTime()).ifPresent(
                startTime -> qw.le(SupplierTransaction::getCreateTime, query.getEndTime())
        );
        return supplierTransactionMapper.selectList(qw);
    }

    /**
     * 新增供应商账户流水
     *
     * @param supplierTransaction 供应商账户流水
     * @return 结果
     */
    @Transactional
    public int insert(SupplierTransaction supplierTransaction) {
        // 获取供应商账户
        Supplier supplier = supplierService.selectById(Long.valueOf(supplierTransaction.getSupplierId()));
        // 判断供应商账户是否存在
        if(null == supplier) {
            throw new RuntimeException("供应商账户不存在");
        }
        // 本次交易额度
        BigDecimal transactionAmount = supplierTransaction.getTransactionAmount();
        // 应付款
        BigDecimal receivableAmount = supplier.getPayableAmount();
        // 剩余金额
        BigDecimal currentBalance = TransAmountUtils.trans(supplierTransaction.getTransactionType(), transactionAmount, receivableAmount);
        // 获取系统当前时间
        LocalDateTime now = LocalDateTime.now();
        // 添加参数
        supplierTransaction.setCreateTime(now);
        // 设置上期余额
        supplierTransaction.setPreviousBalance(receivableAmount);
        // 设置账户当前余额
        supplierTransaction.setCurrentBalance(currentBalance);
        // 设置账户当前余额
        supplier.setPayableAmount(currentBalance);
        // 更新供应商 应付款
        supplier.setPayableAmount(currentBalance);
        // 更新账户额度
        supplierService.update(supplier);
        // 插入供应商记录
        return supplierTransactionMapper.insert(supplierTransaction);
    }

    /**
     * 修改供应商账户流水
     *
     * @param supplierTransaction 供应商账户流水
     * @return 结果
     */
    public int update(SupplierTransaction supplierTransaction) {
        return supplierTransactionMapper.updateById(supplierTransaction);
    }

    /**
     * 批量删除供应商账户流水
     *
     * @param ids 需要删除的供应商账户流水主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return supplierTransactionMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除供应商账户流水信息
     *
     * @param id 供应商账户流水主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return supplierTransactionMapper.updateDelFlagByIds(ids);
    }

    public SupplierTransaction builder(Long orderId, Long supplierId, BigDecimal amount, String transaction, String remark) {
        // 创建交易对象
        SupplierTransaction supplierTransaction = new SupplierTransaction();
        supplierTransaction.setTransactionCode(String.valueOf(orderId));
        supplierTransaction.setSupplierId(String.valueOf(supplierId));
        supplierTransaction.setTransactionType(transaction);
        supplierTransaction.setTransactionAmount(amount);
        supplierTransaction.setRemark(remark);
        supplierTransaction.setReceiptOrderId(orderId.intValue());
        return supplierTransaction;
    }
}
