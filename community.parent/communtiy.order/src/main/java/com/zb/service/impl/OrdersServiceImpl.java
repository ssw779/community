package com.zb.service.impl;

import com.zb.mapper.OrdersMapper;
import com.zb.pojo.Orders;
import com.zb.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Orders getGoodsSalesVolume(Integer goodsId, Integer storeId) {
        Orders orders = ordersMapper.getGoodsSalesVolume(storeId, goodsId);
        System.out.println("orders"+orders);
        return orders;
    }
}
