package cs489.project.dto.order;

import cs489.project.dto.dish.DishResponse;

import java.util.List;

public record OrderRequest(
        String pickupDate,
        String pickupTime,
        int totalPrice,
        List<DishResponse> dishes
) {
}
