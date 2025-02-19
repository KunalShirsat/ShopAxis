package com.tradingapp.controller;

import com.tradingapp.model.Stock;
import com.tradingapp.service.StockData;
import com.tradingapp.service.impl.StockService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")

public class StockServiceController {
    @Autowired
    private StockService stockService;

    @GetMapping("/stock/{symbol}")
    public ResponseEntity<BigDecimal> getStockPrice(@PathVariable String symbol) {
        BigDecimal price = stockService.getStockPrice(symbol);
        return ResponseEntity.ok(price);


    };
}
