
package com.advancedorders.orders.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ShippingClient {
    private final RestClient client;

    public ShippingClient(RestClient.Builder builder,
            @Value("${training.shipping.base-url:http://localhost:8084}") String baseUrl) {
        this.client = builder.clone().baseUrl(baseUrl).build();
    }

    public ShippingQuote quote(String destination, int weightGrams) {
        try {
            ShippingQuote result = client.get()
                    .uri(u -> u.path("/api/shipping/quote")
                            .queryParam("destination", destination)
                            .queryParam("weightGrams", weightGrams)
                            .build())
                    .retrieve()
                    .body(ShippingQuote.class);
            if (result == null) {
                throw new ShippingUnavailableException("Empty shipping response", null);
            }
            return result;
        } catch (org.springframework.web.client.RestClientException ex) {
            throw new ShippingUnavailableException("Shipping call failed", ex);
        }
    }
}
