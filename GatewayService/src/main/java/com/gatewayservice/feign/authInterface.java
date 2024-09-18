package com.gatewayservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-service", url = "http://localhost:8084/api/v1/auth")

public interface authInterface {
    @GetMapping("/validate")
    String validateToken(@RequestParam("token") String token);
}
