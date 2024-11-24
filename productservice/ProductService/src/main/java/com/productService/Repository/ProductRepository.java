package com.productService.Repository;

//import com.productService.Entity.Products;
import com.productService.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
  Optional<Products> findById(Long id);
  List<Products> findByCategoryIgnoreCase(String category);
  List<Products> findByNameContainingIgnoreCase(String keyword);




}
