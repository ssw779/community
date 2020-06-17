package com.zb.mapper;

import com.zb.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper {
    /**
     * 从订单表计算出商品销量
     * @return
     * @param storeNo 店铺编号
     * @param goodsNo 商品编号
     */
    public Orders getGoodsSalesVolume(@Param("storeNo") Integer storeNo, @Param("goodsNo")Integer goodsNo);


}
