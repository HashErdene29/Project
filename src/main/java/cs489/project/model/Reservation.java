package cs489.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reserveId;

    @Column(nullable = false)
    private int numberOfPeople;
    @Column(nullable = false)
    private LocalDate reserveDate;
    @Column(nullable = false)
    private LocalTime reserveTime;
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.PENDING;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private RestaurantManager restaurant;

    public Reservation(int numberOfPeople, LocalDate reserveDate, LocalTime reserveTime, Customer customer, RestaurantManager restaurant) {
        this.numberOfPeople = numberOfPeople;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
        this.customer = customer;
        this.restaurant = restaurant;
    }
}
