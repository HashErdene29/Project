package cs489.project.dto.order;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.dto.dish.DishDto;
import cs489.project.model.OrderStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record OrdeResponse (
        int orderId,
        LocalDate pickupDate,
        LocalTime pickupTime,
        int totalPrice,
        OrderStatus status,
        List<DishDto> dishes,
        CustomerResponse customer
) {
}
