package com.ningmeng.govern.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by ASUS on 2020/2/24.
 */
@SpringBootApplication
@EnableEurekaServer//标示这是一个Eureka服务
public class GovernCenterApplication {
    public static void main(String[] args){
        SpringApplication.run(GovernCenterApplication.class,args);
    }
}
