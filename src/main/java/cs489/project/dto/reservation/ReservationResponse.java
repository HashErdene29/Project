package cs489.project.dto.reservation;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.dto.restaurants.RestaurantResponse;
import cs489.project.model.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse (
    int reserveId,
    int numberOfPeople,
    LocalDate reserveDate,
    LocalTime reserveTime,
    ReservationStatus status,
    CustomerResponse customer,
    RestaurantResponse restaurant
){}
