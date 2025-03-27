package com.cartservice.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
public class Products {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    long price;
    int quantity;
    String category;
    String tags;
    String image;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", tags='" + tags + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}