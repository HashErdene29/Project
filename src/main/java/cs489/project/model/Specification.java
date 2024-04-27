package cs489.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specId;
    @Column(nullable = false)
    private String keyword;

    @ManyToOne
    private RestaurantManager restaurant;

    public Specification(String keyword, RestaurantManager restaurant) {
        this.keyword = keyword;
        this.restaurant = restaurant;
    }
}
