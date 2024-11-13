package com.cartservice.Entity;

import com.cartservice.Client.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<CartItem> items;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Initialize the items list as an empty ArrayList
    private List<CartItem> items = new ArrayList<>();
    @OneToMany
    private List<Product> products = new ArrayList<>();

    // Getters and Setters
}
