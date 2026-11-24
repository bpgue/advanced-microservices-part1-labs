
package com.advancedorders.orders.api;

import com.advancedorders.orders.service.OrderEstimateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderEstimateController {
    private final OrderEstimateService service;
    public OrderEstimateController(OrderEstimateService service) { this.service = service; }

    @GetMapping("/estimate")
    public OrderEstimateResponse estimate(
            @RequestParam("sku") String sku,
            @RequestParam("quantity") int quantity,
            @RequestParam("destination") String destination,
            @RequestParam("weightGrams") int weightGrams) {

        return service.estimate(sku, quantity, destination, weightGrams);
    }
}
