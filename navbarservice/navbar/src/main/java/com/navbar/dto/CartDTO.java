package com.navbar.dto;

import lombok.Data;

@Data
public class CartDTO {
    private int cartCount;

    public CartDTO(int cartCount) {
        this.cartCount = cartCount;
    }

    // Constructors, Getters, Setters
}
