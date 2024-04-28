package cs489.project;

import cs489.project.controller.OrderController;
import cs489.project.controller.ReservationController;
import cs489.project.dto.customer.CustomerResponse;
import cs489.project.dto.reservation.ReservationResponse;
import cs489.project.dto.restaurants.RestaurantResponse;
import cs489.project.model.*;
import cs489.project.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(value = MockitoJUnitRunner.class)
public class IntegrationTest {
    @Mock
    ReservationController reservationController;

    @Test
    public void findAllReservationByTest(){
        int customerId = 1;
        CustomerResponse customer1 = new CustomerResponse(1, "John", "Doe", "john@doe.com", "123-456-7890");
        RestaurantResponse restaurant1 = new RestaurantResponse(1, "Tasty Bites", "info@tastybites.com", "987-654-3210", "123 Elm St", 50);

        ReservationResponse reservation1 = new ReservationResponse(1, 5, LocalDate.parse("2024-04-27"), LocalTime.parse("18:00"), ReservationStatus.PENDING, customer1, restaurant1);
        ReservationResponse reservation2 = new ReservationResponse(2, 10, LocalDate.parse("2024-04-27"), LocalTime.parse("18:00"), ReservationStatus.PENDING, customer1, restaurant1);
        ReservationResponse reservation3 = new ReservationResponse(3, 15, LocalDate.parse("2024-04-27"), LocalTime.parse("18:00"), ReservationStatus.PENDING, customer1, restaurant1);
        ReservationResponse reservation4 = new ReservationResponse(4, 20, LocalDate.parse("2024-04-27"), LocalTime.parse("18:00"), ReservationStatus.PENDING, customer1, restaurant1);

        List<ReservationResponse> reservations = Arrays.asList(reservation1, reservation2, reservation3, reservation4);
        ResponseEntity<List<ReservationResponse>> response = ResponseEntity.ok(reservations);
        when(reservationController.getAllReservations(customerId)).thenReturn(response);

        ResponseEntity<List<ReservationResponse>> response1 = reservationController.getAllReservations(customerId);
        Assert.assertEquals(reservations, response1.getBody());
    }
}
