package com.gilmar.sistemainventario.infrastructure.adapter.input.rest;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "application", "Sistema Inventario API",
                "status", "UP",
                "version", "v1",
                "documentation", "https://sistema-inventario-77tb.onrender.com/swagger-ui/index.html"
        );
    }

    @GetMapping("/api/v1")
    public Map<String, String> apiInfo() {
        return Map.of(
                "name", "Sistema Inventario API",
                "version", "v1",
                "documentation", "https://sistema-inventario-77tb.onrender.com/swagger-ui/index.html"
        );
    }
}