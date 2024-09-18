//package com.gatewayservice.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//public class CorsConfiguration {
//
//    @Bean
//    public CorsWebFilter corsWebFilter() {
//        org.springframework.web.cors.CorsConfiguration corsConfig = new org.springframework.web.cors.CorsConfiguration();
//        corsConfig.addAllowedOrigin("*");
//        corsConfig.setMaxAge(3600L);
//        corsConfig.addAllowedMethod("GET");
//        corsConfig.addAllowedMethod("POST");
//        corsConfig.addAllowedMethod("PUT");
//        corsConfig.addAllowedMethod("DELETE");
//        corsConfig.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }
//}