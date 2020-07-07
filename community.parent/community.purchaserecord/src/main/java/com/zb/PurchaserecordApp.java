package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaserecordApp {
    public static void main(String[] args) {
        SpringApplication.run(PurchaserecordApp.class,args);
    }
}
