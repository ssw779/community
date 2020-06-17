package com.zb.feign.impl;

import com.zb.dto.Dto;
import com.zb.feign.GoodsFeignClient;
import com.zb.pojo.Goods;
import com.zb.pojo.Goodstype;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FallBack implements GoodsFeignClient {
    @Override
    public List<Goodstype> findGoodsType(Goodstype goodstype) {
        System.out.println("服务降级");
        return null;
    }

    @Override
    public List<Goods> findAllGoodsForTiming(Goods goods) {
        List<Goods>goods1=new ArrayList<>();
        goods.setId(1);
        goods.setImgUrl("熔断信息");
        goods.setIndex(1);
        goods.setStoreId(1);
        goods1.add(goods);
        return goods1;
    }

    @Override
    public int updateGoods(Goods goods) {
        return 0;
    }


}
