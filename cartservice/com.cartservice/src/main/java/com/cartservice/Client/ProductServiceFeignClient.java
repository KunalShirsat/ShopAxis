package com.cartservice.Client;

import com.cartservice.Entity.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( name="product-service", url="http://localhost:8080/api/products/v1")
public interface ProductServiceFeignClient {
     @GetMapping("/{id}")
     List<Products> getProductById(@PathVariable String id);
}
