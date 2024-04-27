package cs489.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dishId;
    @Column(nullable = false)
    private String dishName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Menu menu;

    @ManyToMany(mappedBy = "dishes")
    private List<Order> order;

    public Dish(String dishName, int price, String description, Menu menu) {
        this.dishName = dishName;
        this.price = price;
        this.description = description;
        this.menu = menu;
    }
}
