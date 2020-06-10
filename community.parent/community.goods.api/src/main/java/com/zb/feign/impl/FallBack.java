package com.zb.feign.impl;

import com.zb.feign.GoodsFeignClient;
import com.zb.pojo.Goodstype;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FallBack implements GoodsFeignClient {
    @Override
    public List<Goodstype> findGoodsType(Goodstype goodstype) {
        System.out.println("服务降级");
        return null;
    }
}
