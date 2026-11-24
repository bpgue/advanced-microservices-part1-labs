
package com.advancedorders.orders.api;

import com.advancedorders.orders.client.ShippingQuote;

public record OrderEstimateResponse(
        String sku,
        int quantity,
        boolean canBeFulfilled,
        int inventoryAvailable,
        ShippingQuote shipping) {
}
