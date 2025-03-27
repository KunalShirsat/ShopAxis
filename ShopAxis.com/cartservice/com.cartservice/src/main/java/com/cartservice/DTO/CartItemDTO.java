package com.cartservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private String name;
    private String description;
    private long price;
    private int quantity;
    private String category;
    private String tags;
    private String image;
} 