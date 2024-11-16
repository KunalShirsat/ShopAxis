package com.productService.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
//import org.springframework.data.mongodb.core.mapping.Document;

@Data

//@Document(collection = "products")
@Entity
public class Products {

//    `description`, `price`, `quantity`, `category`, `tags`, `images`.
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    long price;
    int quantity;
    String category;
    String tags;
    String image;
    }
