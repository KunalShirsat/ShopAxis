package com.productService.Controller;

import com.productService.Entity.Products;
import com.productService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

//    @PostMapping("v1/add")
//    public Products addProduct(@RequestBody Products product) {
//        return productService.addProduct(product);
//    }

    @PostMapping("/v1/add")
    public ResponseEntity<?> addProducts(@RequestBody List<Products> products) {
        productService.addProduct(products);
        return new ResponseEntity<>("Products added successfully", HttpStatus.OK);
    }


//    @GetMapping("v1/category/{category}")
//    public List<Products> getProductsByCategory(@PathVariable String category) {
//        return productService.getProductsByCategory(category);
//    }

    @GetMapping("v1/search")
    public List<Products> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
    @GetMapping("v1/getAll")
    public List<Products>getAllProducts(){
        return productService.searchAllProducts();
    }

    @GetMapping("v1/{id}")
    public Optional<Products> getProductById(@PathVariable Long id){
       return productService.getProductById(id);
    }
}

