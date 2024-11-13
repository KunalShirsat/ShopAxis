package com.navbar.model;

public class CartResponse {
    private int cartCount;

    public CartResponse(int cartCount) {
        this.cartCount = cartCount;
    }

    public int getCartCount() {
        return cartCount;
    }
}
