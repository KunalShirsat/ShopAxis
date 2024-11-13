package com.navbar.service;

import com.navbar.dto.CartDTO;
import com.navbar.dto.CategoryDTO;
import com.navbar.model.Category;
import com.navbar.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavbarService {

    private final CategoryRepository categoryRepository;

    public NavbarService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public CartDTO getCartCount() {
        // Mocking cart count for demonstration
        int cartCount = 4; // This would be dynamic in a real-world application
        return new CartDTO(cartCount);
    }
}
