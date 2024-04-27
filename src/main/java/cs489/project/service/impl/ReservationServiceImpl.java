package cs489.project.service.impl;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.dto.reservation.ReservationRequest;
import cs489.project.dto.reservation.ReservationResponse;
import cs489.project.dto.restaurants.RestaurantResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.exception.ReservationNotFoundException;
import cs489.project.model.Customer;
import cs489.project.model.Reservation;
import cs489.project.model.ReservationStatus;
import cs489.project.model.RestaurantManager;
import cs489.project.repository.CustomerRepository;
import cs489.project.repository.ResManagerRepository;
import cs489.project.repository.ReservationRepository;
import cs489.project.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private CustomerRepository customerRepository;
    private ResManagerRepository resManagerRepository;
    public ReservationServiceImpl(ReservationRepository reservationRepository, CustomerRepository customerRepository, ResManagerRepository resManagerRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.resManagerRepository = resManagerRepository;
    }

    @Override
    public List<ReservationResponse> getAllReservationsByCustomer(int customerId){
        List<Reservation> list = reservationRepository.findAllByCustomerId(customerId);
        return list.stream()
                .map(a -> new ReservationResponse(
                        a.getReserveId(),
                        a.getNumberOfPeople(),
                        a.getReserveDate(),
                        a.getReserveTime(),
                        a.getStatus(),
                        (new CustomerResponse(
                                a.getCustomer().getCustomerId(),
                                a.getCustomer().getFirstname(),
                                a.getCustomer().getLastname(),
                                a.getCustomer().getEmail(),
                                a.getCustomer().getPhoneHumber()
                        )),
                        (new RestaurantResponse(
                                a.getRestaurant().getResId(),
                                a.getRestaurant().getResName(),
                                a.getRestaurant().getEmail(),
                                a.getRestaurant().getPhoneNumber(),
                                a.getRestaurant().getAddress(),
                                a.getRestaurant().getCapacity()
                        ))
                )).toList();
    }

    @Override
    public List<ReservationResponse> getAllReservationsByRestaurant(int resId){
        List<Reservation> list = reservationRepository.findAllByRestaurant_ResId(resId);
        return list.stream()
                .map(a -> new ReservationResponse(
                        a.getReserveId(),
                        a.getNumberOfPeople(),
                        a.getReserveDate(),
                        a.getReserveTime(),
                        a.getStatus(),
                        (new CustomerResponse(
                                a.getCustomer().getCustomerId(),
                                a.getCustomer().getFirstname(),
                                a.getCustomer().getLastname(),
                                a.getCustomer().getEmail(),
                                a.getCustomer().getPhoneHumber()
                        )),
                        (new RestaurantResponse(
                                a.getRestaurant().getResId(),
                                a.getRestaurant().getResName(),
                                a.getRestaurant().getEmail(),
                                a.getRestaurant().getPhoneNumber(),
                                a.getRestaurant().getAddress(),
                                a.getRestaurant().getCapacity()
                        ))
                )).toList();
    }

    @Override
    public ReservationResponse addNewReservation(int customerId, int resId, ReservationRequest req) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found", customerId)));

        RestaurantManager restaurant = resManagerRepository.findById(resId).get();
        Reservation reservation = new Reservation(
                req.numberOfPeople(),
                LocalDate.parse(req.reserveDate()),
                LocalTime.parse(req.reserveTime()),
                customer,
                restaurant
        );
        Reservation res = reservationRepository.save(reservation);
        return new ReservationResponse(
                res.getReserveId(),
                res.getNumberOfPeople(),
                res.getReserveDate(),
                res.getReserveTime(),
                res.getStatus(),
                (new CustomerResponse(
                        res.getCustomer().getCustomerId(),
                        res.getCustomer().getFirstname(),
                        res.getCustomer().getLastname(),
                        res.getCustomer().getEmail(),
                        res.getCustomer().getPhoneHumber()
                )),
                (new RestaurantResponse(
                        res.getRestaurant().getResId(),
                        res.getRestaurant().getResName(),
                        res.getRestaurant().getEmail(),
                        res.getRestaurant().getPhoneNumber(),
                        res.getRestaurant().getAddress(),
                        res.getRestaurant().getCapacity()
                ))
        );
    }

    @Override
    public ReservationResponse confirmReservation(int reservationId) throws ReservationNotFoundException {
        Reservation res1 = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(String.format("Error: Reservation with Id, %d, is not found", reservationId)));

        res1.setStatus(ReservationStatus.CONFIRMED);
        Reservation res = reservationRepository.save(res1);
        return new ReservationResponse(
                res.getReserveId(),
                res.getNumberOfPeople(),
                res.getReserveDate(),
                res.getReserveTime(),
                res.getStatus(),
                (new CustomerResponse(
                        res.getCustomer().getCustomerId(),
                        res.getCustomer().getFirstname(),
                        res.getCustomer().getLastname(),
                        res.getCustomer().getEmail(),
                        res.getCustomer().getPhoneHumber()
                )),
                (new RestaurantResponse(
                        res.getRestaurant().getResId(),
                        res.getRestaurant().getResName(),
                        res.getRestaurant().getEmail(),
                        res.getRestaurant().getPhoneNumber(),
                        res.getRestaurant().getAddress(),
                        res.getRestaurant().getCapacity()
                ))
        );
    }

    @Override
    public ReservationResponse cancelReservation(int reservationId) throws ReservationNotFoundException {
        Reservation res1 = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(String.format("Error: Reservation with Id, %d, is not found", reservationId)));
        res1.setStatus(ReservationStatus.CANCELLED);
        Reservation res = reservationRepository.save(res1);
        return new ReservationResponse(
                res.getReserveId(),
                res.getNumberOfPeople(),
                res.getReserveDate(),
                res.getReserveTime(),
                res.getStatus(),
                (new CustomerResponse(
                        res.getCustomer().getCustomerId(),
                        res.getCustomer().getFirstname(),
                        res.getCustomer().getLastname(),
                        res.getCustomer().getEmail(),
                        res.getCustomer().getPhoneHumber()
                )),
                (new RestaurantResponse(
                        res.getRestaurant().getResId(),
                        res.getRestaurant().getResName(),
                        res.getRestaurant().getEmail(),
                        res.getRestaurant().getPhoneNumber(),
                        res.getRestaurant().getAddress(),
                        res.getRestaurant().getCapacity()
                ))
        );
    }
}
