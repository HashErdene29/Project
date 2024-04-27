package cs489.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(nullable = false)
    private LocalDate pickupDate;
    @Column(nullable = false)
    private LocalTime pickupTime;
    @Column(nullable = false)
    private int totalPrice;
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @ManyToMany
    @JoinTable(
            name = "orders_dishes",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    @ManyToOne
    private Customer customer;

    public Order(LocalDate pickupDate, LocalTime pickupTime, int totalPrice, Customer customer) {
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }
}
