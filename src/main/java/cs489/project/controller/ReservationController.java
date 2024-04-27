package cs489.project.controller;

import cs489.project.dto.reservation.ReservationRequest;
import cs489.project.dto.reservation.ReservationResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.exception.ReservationNotFoundException;
import cs489.project.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project/public/reservation")
public class ReservationController {
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

//    @GetMapping("/list/{customerId}")
//    public ResponseEntity<List<ReservationResponse>> getAllReservations(@PathVariable int customerId) {
//        return ResponseEntity.ok(reservationService.getAllReservationsByCustomer(customerId));
//    }

    @GetMapping("/list/{resId}")
    public ResponseEntity<List<ReservationResponse>> getAllReservationsByRestaurant(@PathVariable int resId) {
        return ResponseEntity.ok(reservationService.getAllReservationsByRestaurant(resId));
    }

    @PostMapping ("/addnew/{customerId}/{resId}")
    public ResponseEntity<ReservationResponse> addNewReservation(@PathVariable int customerId,
                                                                 @PathVariable int resId,
                                                                 @RequestBody ReservationRequest req) throws CustomerNotFoundException {
        return ResponseEntity.ok(reservationService.addNewReservation(customerId, resId, req));
    }

    @GetMapping("confirm/{reserveId}")
    public ResponseEntity<ReservationResponse> confirmReservation(@PathVariable int reserveId) throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.confirmReservation(reserveId));
    }

    @GetMapping("cancel/{reserveId}")
    public ResponseEntity<ReservationResponse> cancelReservation(@PathVariable int reserveId) throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.cancelReservation(reserveId));
    }

}
