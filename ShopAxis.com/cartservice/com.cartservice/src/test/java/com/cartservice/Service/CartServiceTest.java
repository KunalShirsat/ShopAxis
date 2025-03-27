package com.cartservice.Service;

import com.cartservice.Client.ProductServiceFeignClient;
import com.cartservice.Entity.Products;
import com.cartservice.Exception.ResourceNotFoundException;
import com.cartservice.Repository.CartProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.ResourceAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartProducts cartProducts;

    @Mock
    private ProductServiceFeignClient productServiceFeignClient;

    @InjectMocks
    private CartService cartService;

    private Products testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Products();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(100);
        testProduct.setQuantity(1);
        testProduct.setCategory("Test Category");
        testProduct.setTags("test,product");
        testProduct.setImage("test-image.jpg");
    }

    @Test
    void addToCart_ValidId_ReturnsProduct() {
        // Arrange
        String productId = "1";
        when(productServiceFeignClient.getProductById(1L)).thenReturn(Optional.of(testProduct));
        when(cartProducts.save(any(Products.class))).thenReturn(testProduct);

        // Act
        Products result = cartService.addToCart(productId);

        // Assert
        assertNotNull(result);
        assertEquals(testProduct.getId(), result.getId());
        assertEquals(testProduct.getName(), result.getName());
        verify(productServiceFeignClient, times(1)).getProductById(1L);
        verify(cartProducts, times(1)).save(any(Products.class));
    }

    @Test
    void addToCart_ProductNotFound_ThrowsResourceNotFoundException() {
        // Arrange
        String productId = "1";
        when(productServiceFeignClient.getProductById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> cartService.addToCart(productId));
        verify(productServiceFeignClient, times(1)).getProductById(1L);
        verify(cartProducts, never()).save(any(Products.class));
    }

    @Test
    void addToCart_InvalidId_ThrowsIllegalArgumentException() {
        // Arrange
        String productId = "invalid";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cartService.addToCart(productId));
        verify(productServiceFeignClient, never()).getProductById(anyLong());
        verify(cartProducts, never()).save(any(Products.class));
    }

    @Test
    void addToCart_ServiceUnavailable_ThrowsResourceAccessException() {
        // Arrange
        String productId = "1";
        when(productServiceFeignClient.getProductById(1L)).thenThrow(new ResourceAccessException("Service unavailable"));

        // Act & Assert
        assertThrows(ResourceAccessException.class, () -> cartService.addToCart(productId));
        verify(productServiceFeignClient, times(1)).getProductById(1L);
        verify(cartProducts, never()).save(any(Products.class));
    }

    @Test
    void getCartItems_ReturnsAllItems() {
        // Arrange
        List<Products> expectedProducts = Arrays.asList(testProduct);
        when(cartProducts.findAll()).thenReturn(expectedProducts);

        // Act
        List<Products> result = cartService.getCartItems();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getId(), result.get(0).getId());
        verify(cartProducts, times(1)).findAll();
    }

    @Test
    void getCartItemsPaged_ReturnsPagedItems() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<Products> expectedProducts = Arrays.asList(testProduct);
        Page<Products> expectedPage = new PageImpl<>(expectedProducts, pageable, 1);
        when(cartProducts.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Products> result = cartService.getCartItemsPaged(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(testProduct.getId(), result.getContent().get(0).getId());
        verify(cartProducts, times(1)).findAll(pageable);
    }

    @Test
    void deleteCartItem_ExistingId_DeletesItem() {
        // Arrange
        Long productId = 1L;
        when(cartProducts.findById(productId)).thenReturn(Optional.of(testProduct));
        doNothing().when(cartProducts).deleteById(productId);

        // Act
        cartService.deleteCartItem(productId);

        // Assert
        verify(cartProducts, times(1)).findById(productId);
        verify(cartProducts, times(1)).deleteById(productId);
    }

    @Test
    void deleteCartItem_NonExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        Long productId = 1L;
        when(cartProducts.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> cartService.deleteCartItem(productId));
        verify(cartProducts, times(1)).findById(productId);
        verify(cartProducts, never()).deleteById(anyLong());
    }
}