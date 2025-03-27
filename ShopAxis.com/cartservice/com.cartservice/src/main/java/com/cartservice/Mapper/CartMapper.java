package com.cartservice.Mapper;

import com.cartservice.DTO.CartItemDTO;
import com.cartservice.DTO.CartResponseDTO;
import com.cartservice.Entity.Products;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    /**
     * Convert a Products entity to a CartItemDTO
     */
    public CartItemDTO toCartItemDTO(Products product) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategory(product.getCategory());
        dto.setTags(product.getTags());
        dto.setImage(product.getImage());
        return dto;
    }
    
    /**
     * Convert a list of Products entities to a list of CartItemDTOs
     */
    public List<CartItemDTO> toCartItemDTOs(List<Products> products) {
        return products.stream()
                .map(this::toCartItemDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Create a CartResponseDTO from a list of Products
     */
    public CartResponseDTO toCartResponseDTO(List<Products> products) {
        List<CartItemDTO> items = toCartItemDTOs(products);
        int itemCount = items.size();
        long totalPrice = items.stream()
                .mapToLong(item -> item.getPrice() * item.getQuantity())
                .sum();
        
        return new CartResponseDTO(items, itemCount, totalPrice);
    }
    
    /**
     * Convert a CartItemDTO to a Products entity
     */
    public Products toProduct(CartItemDTO dto) {
        Products product = new Products();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(dto.getCategory());
        product.setTags(dto.getTags());
        product.setImage(dto.getImage());
        return product;
    }
} 