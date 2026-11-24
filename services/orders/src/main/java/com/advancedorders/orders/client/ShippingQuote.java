
package com.advancedorders.orders.client;

public record ShippingQuote(String destination, int weightGrams, long priceCents,
        String currency, int estimatedDays, String provider) {
}
