package com.zb;

import com.zb.tools.CanalTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 别忘了开启canal服务端
 * startup.bat
 * d:canal/bin/startup.bat
 *
 * @author 孙硕威
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BannerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BannerApp.class, args);
        CanalTools bean = run.getBean(CanalTools.class);
        bean.execution();
    }


    @Bean
    public RestTemplate createRestTemplate() {

        return new RestTemplate();
    }
}
