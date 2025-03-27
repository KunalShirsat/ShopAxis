package com.cartservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {
    private List<CartItemDTO> items;
    private int itemCount;
    private long totalPrice;
    
    // Pagination fields
    private int currentPage;
    private int totalPages;
    private long totalItems;
    
    public CartResponseDTO(List<CartItemDTO> items, int itemCount, long totalPrice) {
        this.items = items;
        this.itemCount = itemCount;
        this.totalPrice = totalPrice;
    }
} 