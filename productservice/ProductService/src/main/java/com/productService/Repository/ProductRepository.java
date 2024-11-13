package com.productService.Repository;

//import com.productService.Entity.Products;
import com.productService.Entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Products, Long> {
  List<Products> findById(String id);
  List<Products> findByCategoryIgnoreCase(String category);
  List<Products> findByNameContainingIgnoreCase(String keyword);




}
