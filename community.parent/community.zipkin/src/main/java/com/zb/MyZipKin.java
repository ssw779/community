package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * 链路追踪
 * localhost:8004
 *
 * @author 孙硕威
 */
@SpringBootApplication
@EnableZipkinServer
public class MyZipKin {
    public static void main(String[] args) {
        SpringApplication.run(MyZipKin.class, args);
    }
}
