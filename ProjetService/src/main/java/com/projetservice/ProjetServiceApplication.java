package com.projetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient  "Using restTemplate"
public class ProjetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetServiceApplication.class, args);
    }

}
