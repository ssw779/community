package com.zb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 商品启动类
 * @author 孙硕威
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //调用微服务的客户端， 就是代表consumer
@MapperScan("com.zb.mapper")
public class GoodsServer {
    public static void main(String[] args) {
        SpringApplication.run(GoodsServer.class,args);
    }
}
