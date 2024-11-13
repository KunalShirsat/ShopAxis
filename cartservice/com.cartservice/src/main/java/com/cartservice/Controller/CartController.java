package com.cartservice.Controller;


import com.cartservice.Client.ProductServiceFeignClient;
import com.cartservice.Entity.Products;
import com.cartservice.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;

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
    @PostMapping("/addtocart")
    public List<Products> addToCart(@RequestParam String id){




   return  cartService.addToCart(id);

    }
    @GetMapping("/getcart")
    public List<Products> getCartItems(){

        return cartService.getCartItems();


    }
    @DeleteMapping("/remove")
public void removeItemFromCart( @RequestParam Long id){

      cartService.deleteCartItem(id);

        }


//    @DeleteMapping("/{userId}/clear")
//    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
//        cartService.clearCart(userId);
//        return ResponseEntity.ok().build();
//    }

}
