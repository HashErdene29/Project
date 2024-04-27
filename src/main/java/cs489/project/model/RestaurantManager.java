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
public class RestaurantManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resId;
    @Column(nullable = false)
    private String resName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Specification> keywords;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus;

    public RestaurantManager(String resName, String email, String phoneNumber, String address, int capacity) {
        this.resName = resName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.capacity = capacity;
    }
}
