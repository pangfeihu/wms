package com.cyl.wms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyl.wms.domain.entity.Customer;
import com.cyl.wms.enums.Transaction;
import com.cyl.wms.utils.TransAmountUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.CustomerTransactionMapper;
import com.cyl.wms.domain.entity.CustomerTransaction;
import com.cyl.wms.domain.query.CustomerTransactionQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户账户流水Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class CustomerTransactionService {
    @Autowired
    private CustomerTransactionMapper customerTransactionMapper;

    @Autowired
    private CustomerService customerService;

    /**
     * 查询客户账户流水
     *
     * @param id 客户账户流水主键
     * @return 客户账户流水
     */
    public CustomerTransaction selectById(Integer id) {
        return customerTransactionMapper.selectById(id);
    }

    /**
     * 查询客户账户流水列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 客户账户流水
     */
    public List<CustomerTransaction> selectList(CustomerTransactionQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), "create_time desc");
        }
        LambdaQueryWrapper<CustomerTransaction> qw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getCustomerId())){
            qw.eq(CustomerTransaction::getCustomerId, query.getCustomerId());
        }
        if (!StringUtils.isEmpty(query.getTransactionCode())){
            qw.eq(CustomerTransaction::getTransactionCode, query.getTransactionCode());
        }
        if (!StringUtils.isEmpty(query.getTransactionType())){
            qw.eq(CustomerTransaction::getTransactionType, query.getTransactionType());
        }
        Optional.ofNullable(query.getStartTime()).ifPresent(
                startTime -> qw.ge(CustomerTransaction::getCreateTime, query.getStartTime())
        );
        Optional.ofNullable(query.getEndTime()).ifPresent(
                startTime -> qw.le(CustomerTransaction::getCreateTime, query.getEndTime())
        );
        return customerTransactionMapper.selectList(qw);
    }

    /**
     * 新增客户账户流水
     *
     * @param customerTransaction 客户账户流水
     * @return 结果
     */
    @Transactional
    public int insert(CustomerTransaction customerTransaction) {
        // 获取用户信息
        Customer customer = customerService.selectById(Long.valueOf(customerTransaction.getCustomerId()));
        // 判断用户是否存在
        if (customer == null){
            throw new RuntimeException("客户账户不存在");
        }
        // 本次交易额度
        BigDecimal transactionAmount = customerTransaction.getTransactionAmount();
        // 上次账户额度
        BigDecimal receivableAmount = customer.getReceivableAmount();
        // 剩余金额
        BigDecimal currentBalance = TransAmountUtils.trans(customerTransaction.getTransactionType(), transactionAmount, receivableAmount);
        // 获取系统当前时间
        LocalDateTime now = LocalDateTime.now();
        // 添加参数
        customerTransaction.setCreateTime(now);
        // 设置账户当前余额
        customerTransaction.setCurrentBalance(currentBalance);
        // 设置账户当前余额
        customer.setReceivableAmount(currentBalance);
        // 设置上期余额
        customerTransaction.setPreviousBalance(receivableAmount);
        // 更新账户信息
        customerService.update(customer);
        // 插入交易信息
        return customerTransactionMapper.insert(customerTransaction);
    }



    /**
     * 修改客户账户流水
     *
     * @param customerTransaction 客户账户流水
     * @return 结果
     */
    public int update(CustomerTransaction customerTransaction) {
        return customerTransactionMapper.updateById(customerTransaction);
    }

    /**
     * 批量删除客户账户流水
     *
     * @param ids 需要删除的客户账户流水主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return customerTransactionMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除客户账户流水信息
     *
     * @param id 客户账户流水主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return customerTransactionMapper.updateDelFlagByIds(ids);
    }


    public CustomerTransaction builder(Long orderId,Long customerId, BigDecimal amount,String transaction,String remark) {
        // 创建交易对象
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setTransactionCode(String.valueOf(orderId));
        customerTransaction.setCustomerId(String.valueOf(customerId));
        customerTransaction.setTransactionType(transaction);
        customerTransaction.setTransactionAmount(amount);
        customerTransaction.setShipmentOrderId(orderId.intValue());
        customerTransaction.setRemark(remark);
        return customerTransaction;
    }



}
