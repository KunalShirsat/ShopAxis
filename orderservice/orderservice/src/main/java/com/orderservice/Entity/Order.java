package com.orderservice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Order.java
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
}