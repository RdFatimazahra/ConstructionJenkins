package com.tacheservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class TacheServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacheServiceApplication.class, args);
    }
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
