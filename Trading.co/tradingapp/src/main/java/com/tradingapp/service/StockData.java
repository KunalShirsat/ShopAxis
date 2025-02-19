package com.tradingapp.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public interface StockData {
    public BigDecimal getStockPrice(String symbol);
}
