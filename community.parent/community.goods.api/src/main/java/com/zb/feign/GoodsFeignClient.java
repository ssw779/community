package com.zb.feign;

import com.zb.feign.impl.FallBack;
import com.zb.pojo.Goodstype;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "goodsServer", fallback = FallBack.class)
public interface GoodsFeignClient {
    @GetMapping(value = "/findGoodsType")
    /**
     * 查询商品类型
     */
    public List<Goodstype> findGoodsType(@RequestBody Goodstype goodstype);
}
