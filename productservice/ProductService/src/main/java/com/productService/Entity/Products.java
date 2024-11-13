package com.productService.Entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document(collection = "products")
public class Products {
//    `description`, `price`, `quantity`, `category`, `tags`, `images`.

    String id;
    String name;
    String description;
    long price;
    int quantity;
    String category;
    String tags;
    String image;
    }
