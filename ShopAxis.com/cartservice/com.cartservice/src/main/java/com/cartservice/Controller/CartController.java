package com.cartservice.Controller;

import com.cartservice.Client.ProductServiceFeignClient;
import com.cartservice.DTO.CartItemDTO;
import com.cartservice.DTO.CartResponseDTO;
import com.cartservice.Entity.Products;
import com.cartservice.Mapper.CartMapper;
import com.cartservice.Service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@Tag(name = "Cart", description = "The Cart API for managing shopping cart operations")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;
    
    @Autowired
    private CartMapper cartMapper;

//    @GetMapping("/test/{id}")
//    public ProductServiceFeignClient testProductService (@PathVariable Long id){
//        return cartService.testProductService(id);
//
//    }




//    @GetMapping("/{userId}")
//    public ResponseEntity<Optional<Cart>> getCart(@PathVariable String userId){
//
//
//        return ResponseEntity.ok(cartService.getCartByUserId(userId)) ;
//    }


//    @PostMapping("add/{userId}")
//public  ResponseEntity<Cart> addItemToCart(@PathVariable String userId, @RequestBody CartItem cartItem){
//
//
//        return ResponseEntity.ok(cartService.addToCart(userId, cartItem));
//}
    /**
     * Add a product to the cart
     */
    @Operation(summary = "Add a product to the cart", description = "Adds a product to the cart by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product added to cart", 
                     content = { @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = CartItemDTO.class)) }),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "400", description = "Invalid product ID"),
        @ApiResponse(responseCode = "503", description = "Product service unavailable")
    })
    @PostMapping("/addtocart")
    public ResponseEntity<CartItemDTO> addToCart(
            @Parameter(description = "ID of the product to add to cart", required = true)
            @RequestParam String id) {
        logger.info("Adding product with ID {} to cart", id);
        Products addedProduct = cartService.addToCart(id);
        return ResponseEntity.ok(cartMapper.toCartItemDTO(addedProduct));
    }
    
    /**
     * Get all items in the cart
     */
    @Operation(summary = "Get all cart items", description = "Retrieves all products in the cart with count and total price")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart items retrieved successfully",
                     content = { @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = CartResponseDTO.class)) })
    })
    @GetMapping("/getcart")
    public ResponseEntity<CartResponseDTO> getCartItems() {
        logger.info("Retrieving all cart items");
        List<Products> cartItems = cartService.getCartItems();
        CartResponseDTO response = cartMapper.toCartResponseDTO(cartItems);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Remove an item from the cart
     */
    @Operation(summary = "Remove an item from cart", description = "Removes a product from the cart by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product removed from cart"),
        @ApiResponse(responseCode = "404", description = "Product not found in cart")
    })
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeItemFromCart(
            @Parameter(description = "ID of the product to remove from cart", required = true)
            @RequestParam Long id) {
        logger.info("Removing product with ID {} from cart", id);
        cartService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }


//    @DeleteMapping("/{userId}/clear")
//    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
//        cartService.clearCart(userId);
//        return ResponseEntity.ok().build();
//    }

}
