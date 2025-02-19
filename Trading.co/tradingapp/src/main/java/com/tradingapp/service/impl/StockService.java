package com.tradingapp.service.impl;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
@Service
public class StockService {
    private static final String API_KEY = "YOUR_API_KEY"; // Store securely

    public BigDecimal getStockPrice(String symbol) {
        try {
            String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="
                    + symbol + "&apikey=" + API_KEY;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getBody() == null) {
                throw new RuntimeException("Empty response from API");
            }

            JSONObject json = new JSONObject(response.getBody());

            // Validate response structure
            if (!json.has("Global Quote") || !json.getJSONObject("Global Quote").has("05. price")) {
                throw new RuntimeException("Invalid API response format");
            }

            return new BigDecimal(json.getJSONObject("Global Quote").getString("05. price"));

        } catch (Exception e) {
            System.err.println("Error fetching stock price: " + e.getMessage());
            return BigDecimal.ZERO; // Return a default value or handle errors appropriately
        }
    }
}
