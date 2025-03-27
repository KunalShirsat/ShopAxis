package com.cartservice.Service;

import com.cartservice.Client.ProductServiceFeignClient;
import com.cartservice.Entity.Products;
import com.cartservice.Exception.ResourceNotFoundException;
import com.cartservice.Repository.CartProducts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartProducts cartProducts;

    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;

    /**
     * Add product to cart by fetching it from the product service
     * 
     * @param id The product ID to add to cart
     * @return The product that was added to the cart
     * @throws ResourceNotFoundException if product is not found
     * @throws ResourceAccessException if product service is unavailable
     */
    public Products addToCart(String id) {
        try {
            Long productId = Long.parseLong(id);
            Optional<Products> productResponse = productServiceFeignClient.getProductById(productId);
            
            if (!productResponse.isPresent()) {
                throw new ResourceNotFoundException("Product", "id", id);
            }
            
            Products product = productResponse.get();
            logger.info("Product retrieved from product service: {}", product);
            return cartProducts.save(product);
        } catch (NumberFormatException e) {
            logger.error("Invalid product ID format: {}", id, e);
            throw new IllegalArgumentException("Invalid product ID format. Must be a number.");
        } catch (ResourceAccessException e) {
            logger.error("Failed to communicate with product service", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error adding product to cart", e);
            throw e;
        }
    }

    /**
     * Retrieve all items in the cart
     * 
     * @return List of products in the cart
     */
    public List<Products> getCartItems() {
        logger.debug("Fetching all cart items");
        List<Products> products = cartProducts.findAll();
        logger.info("Products fetched: {}", products);
        return products;
    }
    
    /**
     * Retrieve cart items with pagination
     * 
     * @param pageable Pagination information
     * @return Page of products in the cart
     */
    public Page<Products> getCartItemsPaged(Pageable pageable) {
        logger.debug("Fetching cart items with pagination: {}", pageable);
        Page<Products> productsPage = cartProducts.findAll(pageable);
        logger.info("Products page fetched, total elements: {}", productsPage.getTotalElements());
        return productsPage;
    }
    
    /**
     * Delete an item from the cart
     * 
     * @param id The product ID to remove from cart
     * @throws ResourceNotFoundException if the product doesn't exist in the cart
     */
    public void deleteCartItem(Long id) {
        logger.debug("Removing product with ID {} from cart", id);
        
        // Check if product exists before deleting
        Optional<Products> product = cartProducts.findById(id);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException("CartItem", "id", id);
        }
        
        cartProducts.deleteById(id);
        logger.info("Product with ID {} removed from cart", id);
    }
}
