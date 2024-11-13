package com.navbar.controller;

import com.navbar.dto.CartDTO;
import com.navbar.dto.CategoryDTO;
import com.navbar.service.NavbarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/navbar")
@CrossOrigin(origins = "http://localhost:3000")
public class NavbarController {
    private final NavbarService navbarService;

    public NavbarController(NavbarService navbarService) {
        this.navbarService = navbarService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = navbarService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/cart/count")
    public ResponseEntity<CartDTO> getCartCount() {
        CartDTO cartDTO = navbarService.getCartCount();
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
