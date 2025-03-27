package com.cartservice.Repository;

import com.cartservice.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProducts extends JpaRepository<Products,Long> {

//    Optional<Products> findById(Long  id);
//    public void deleteById(Long  id);
}
