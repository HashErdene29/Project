package cs489.project.repository;

import cs489.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByCustomerId(int customerId);
    List<Reservation> findAllByRestaurant_ResId(int resId);
}
