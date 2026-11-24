
package com.advancedorders.orders.service;

import com.advancedorders.orders.api.OrderEstimateResponse;
import com.advancedorders.orders.client.InventoryClient;
import com.advancedorders.orders.client.ShippingClient;
import org.springframework.stereotype.Service;

@Service
public class OrderEstimateService {
    private final InventoryClient inventory;
    private final ShippingClient shipping;

    public OrderEstimateService(InventoryClient inventory, ShippingClient shipping) {
        this.inventory = inventory;
        this.shipping = shipping;
    }

    public OrderEstimateResponse estimate(
            String sku, int quantity, String destination, int weightGrams) {
        if (sku == null || sku.isBlank() || quantity < 1
                || destination == null || !destination.matches("[A-Z]{2}")
                || weightGrams < 1 || weightGrams > 10000) {
            throw new IllegalArgumentException("Invalid estimate request");
        }

        var stock = inventory.getInventory(sku);
        boolean enough = stock.available() >= quantity;
        var quote = enough ? shipping.quote(destination, weightGrams) : null;

        return new OrderEstimateResponse(
                stock.sku(), quantity, enough, stock.available(), quote);
    }
}
