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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;
    @Column(nullable = false)
    private String menuName;

    @ManyToOne
    private RestaurantManager restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    public Menu(String menuName, RestaurantManager restaurant) {
        this.menuName = menuName;
        this.restaurant = restaurant;
    }
}
