package com.tradingapp.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

//@Entity
@Data
public class Stock {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;
    private String symbol;
    private String name;
    private BigDecimal price;
}

