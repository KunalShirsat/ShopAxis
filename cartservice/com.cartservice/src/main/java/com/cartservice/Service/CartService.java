package com.cartservice.Service;

import com.cartservice.Client.ProductServiceFeignClient;
import com.cartservice.Entity.Products;
import com.cartservice.Repository.CartProducts;
import com.cartservice.Repository.CartRepo;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartProducts cartProducts;


    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;

//    public Optional<Cart> getCartByUserId(long userId){
//
//       Optional<Cart> products=  cartRepo.findByUserId(userId);
//        logger.info(String.valueOf(products));
//        return  cartRepo.save(productServiceFeignClient.getProductById(id));
//
//
//
//    }


//    public Cart addItemToCart(String userId, CartItem cartItem){
//     Optional<Cart> cart=getCartByUserId(userId);
////         return  cartRepo.save(cartItem);
//        // Check if item already exists in the cart, if so update it
//        Optional<CartItem> existingItem = cart.getItems().stream()
//                .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
//                .findFirst();
//}


//    public Cart addToCart(String userId, CartItem item) {
//        Optional<Cart> optionalCart = getCartByUserId(userId);
//
//        Cart cart;
//        if (optionalCart.isPresent()) {
//            cart = optionalCart.get();
//
//            // Check if item already exists in the cart
//            Optional<CartItem> existingItem = cart.getItems().stream()
//                    .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
//                    .findFirst();
//
//            if (existingItem.isPresent()) {
//                // Update the existing item's quantity and price
//                CartItem cartItem = existingItem.get();
//                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
//                cartItem.setPrice(item.getPrice());
//            } else {
//                // Add new item to the cart
//                cart.getItems().add(item);
//            }
//
//        } else {
//            // Create a new cart for the user and add the item
//            cart = new Cart();
//            cart.setUserId(userId);
//            cart.getItems().add(item);
//        }
//
//        // Save the cart and return it
//        return cartRepo.save(cart);
//    }

//    Add to cart

    public List<Products> addToCart(String id) {
        List<Products> productResponse = productServiceFeignClient.getProductById(id);
     return    cartProducts.saveAll(productResponse);
    }


//    Get cart data

    public List<Products> getCartItems(){

        List<Products> products=cartProducts.findAll();
        logger.info("Products fetched: " + products);
        return  products;
   }
    // Delete a CartItem by its ID
    public void deleteCartItem(Long id) {
        cartProducts.deleteById(id);


    }


















}
//
//        ResponseEntity<List<Products>> productResponse = productServiceFeignClient.getProductById(id);
//       List<Product> products =productResponse.getBody();
//        if (products != null && !products.isEmpty()) {
//            Product product = products.get(0);
//            cartProducts.save(product);


//        Cart cart = new Cart();
//        Products products1=new Products();
//      cart.setProducts(products);
//      cart.save(cart);
//        return ResponseEntity.ok(products);


//    public Cart removeItemFromCart(String userId, String productId) {
//        Optional<Cart> cartOptional = getCartByUserId(userId);
//
//        // Check if the cart exists, if not throw an exception
//        Cart cart = cartOptional.orElseThrow(() ->
//                new RuntimeException("Cart not found for user: " + userId));
//
//        // Check if the cart has items, if not throw an exception
//        if (cart.getItems() == null || cart.getItems().isEmpty()) {
//            throw new RuntimeException("No items found in the cart for user: " + userId);
//        }

//        // Remove the item from the cart if it exists
//        cart.setItems(cart.getItems().stream()
//                .filter(item -> !item.getProductId().equals(productId))
//                .collect(Collectors.toList()));
//
//        // Save the updated cart and return it
//        return cartRepo.save(cart);
//    }


//    public void clearCart(String userId) {
//        Optional<Cart> cartOptional = getCartByUserId(userId);
//
//        // Check if the cart exists, if not throw an exception
//        Cart cart = cartOptional.orElseThrow(() ->
//                new RuntimeException("Cart not found for user: " + userId));
//
//        // Clear the cart items and save the empty cart
//        cart.getItems().clear();
//        cartRepo.save(cart);
//    }
