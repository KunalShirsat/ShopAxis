package com.productService.Service;

import com.productService.Entity.Products;
import com.productService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(List<Products> product) {
        productRepository.saveAll(product);

//        return Collections.singletonList(productRepository.save(product));
    }

    public List<Products> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<Products> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    public List<Products>searchAllProducts(){
        return productRepository.findAll();
    }

    public List<Products >getProductById(String id){

        return  productRepository.findById(id);

    }
}
