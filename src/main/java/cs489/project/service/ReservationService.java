package cs489.project.service;

import cs489.project.dto.reservation.ReservationRequest;
import cs489.project.dto.reservation.ReservationResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.exception.ReservationNotFoundException;

import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getAllReservationsByCustomer(int customerId);
    List<ReservationResponse> getAllReservationsByRestaurant(int resId);
    ReservationResponse addNewReservation(int customerId, int resId, ReservationRequest reservationDto) throws CustomerNotFoundException;
    ReservationResponse confirmReservation(int reserveId) throws ReservationNotFoundException;
    ReservationResponse cancelReservation(int reserveId) throws ReservationNotFoundException;

}
