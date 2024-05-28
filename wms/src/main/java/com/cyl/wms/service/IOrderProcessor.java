package com.cyl.wms.service;

import com.cyl.wms.domain.WmGoodsUnit;
import com.cyl.wms.domain.WmOrder;
import com.cyl.wms.domain.WmOrderDetail;
import org.springframework.beans.factory.InitializingBean;

public interface IOrderProcessor extends InitializingBean {

    public void create(WmOrder order);

    public void complete(WmOrder order);

    public void calcAmount(WmOrderDetail orderDetail, WmGoodsUnit goodsUnit);

}
