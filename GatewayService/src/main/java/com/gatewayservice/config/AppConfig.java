package com.gatewayservice.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

//Configuration RestTemplate :
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//}
    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters();
    }
}
