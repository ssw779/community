package com.zb.service;

import com.zb.pojo.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrdersService {
    /**
     * 根据商品编号和店铺编号查询销量
     *
     * @param goodsId 商品编号
     * @param storeId 店铺编号
     * @return 返回订单信息，商品编号和商品的销售量
     */
    public Orders getGoodsSalesVolume(Integer goodsId, Integer storeId);
}
