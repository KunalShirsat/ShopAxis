package com.cartservice.Client;

import com.cartservice.Entity.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="product-service")
public interface ProductServiceFeignClient {
     @GetMapping("/api/products/v1/{id}")
     Optional<Products> getProductById(@PathVariable Long id);
}
