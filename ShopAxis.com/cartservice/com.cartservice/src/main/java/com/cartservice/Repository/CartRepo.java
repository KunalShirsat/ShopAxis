package com.cartservice.Repository;

import com.cartservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
     Optional <Cart> findByUserId(String userId);

}
