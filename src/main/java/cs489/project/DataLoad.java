package cs489.project;

import cs489.project.model.*;
import cs489.project.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class DataLoad implements CommandLineRunner {
    private CustomerRepository customerRepository;
    private ResManagerRepository resManagerRepository;
    private ReservationRepository reservationRepository;
    private MenuRepository menuRepository;
    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private SpecificationRepository specificationRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public DataLoad(CustomerRepository customerRepository, ResManagerRepository resManager, ReservationRepository res,
                    MenuRepository menuRepository, OrderRepository orderRepository, SpecificationRepository specification,
                    DishRepository dish, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.resManagerRepository = resManager;
        this.reservationRepository = res;
        this.menuRepository  = menuRepository;
        this.orderRepository = orderRepository;
        this.specificationRepository = specification;
        this.dishRepository = dish;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataLoad.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_CUSTOMER");
        Role role3 = new Role("ROLE_RESTAURANT");
        roleRepository.saveAll(Arrays.asList(role1, role2, role3));

        User newAdminUser = new User(null, "Admin", "Admin", "Admin", "admin",
                passwordEncoder.encode("test1234"), "admin@fairfieldrestaurant.com",
                true,true, true, true);
        User newCustomer = new User(null, "User", "User", "User", "user",
                passwordEncoder.encode("test1234"), "user@fairfieldrestaurant.com",
                true,true, true, true);
        User newResManager = new User(null, "Manager", "Manager", "Manager", "manager",
                passwordEncoder.encode("test1234"), "manager@fairfieldrestaurant.com",
                true,true, true, true);
        newAdminUser.setRoles(Arrays.asList(role1));
        newCustomer.setRoles(Arrays.asList(role2));
        newResManager.setRoles(Arrays.asList(role3));
        userRepository.saveAll(Arrays.asList(newAdminUser, newCustomer, newResManager));


        Customer customer1 = new Customer("John", "Doe", "john@doe.com", "123-456-7890");
        Customer customer2 = new Customer("Janet", "Akar", "janet@akar.com", "234-456-7890");

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        RestaurantManager restaurant1 = new RestaurantManager("Tasty Bites", "info@tastybites.com", "987-654-3210", "123 Elm St", 50);
        RestaurantManager restaurant2 = new RestaurantManager("Spice Avenue", "info@spiceavenue.com", "456-789-0123", "456 Maple Ave",  40);

        resManagerRepository.saveAll(Arrays.asList(restaurant1, restaurant2));

        Specification keyword1 = new Specification("Italian", restaurant1);
        Specification keyword2 = new Specification("Indian", restaurant2);
        Specification keyword3 = new Specification("Pasta", restaurant1);
        Specification keyword4 = new Specification("Pizza", restaurant1);
        specificationRepository.saveAll(Arrays.asList(keyword1, keyword2, keyword3, keyword4));

        Menu menu1 = new Menu("Main Dish", restaurant1);
        Menu menu2 = new Menu("Appetizer", restaurant1);
        menuRepository.saveAll(Arrays.asList(menu1, menu2));

        Dish dish1 = new Dish("Alfredo", 12, "Pasta noodles, alfredo sauce", menu1);
        Dish dish2 = new Dish("Traditional pasta", 11, "Pasta noodles, marinara sauce", menu1);
        dishRepository.saveAll(Arrays.asList(dish1, dish2));

        Reservation reservation1 = new Reservation(5, LocalDate.parse("2024-04-25"), LocalTime.parse("18:00"), customer1, restaurant1);
        reservationRepository.saveAll(Arrays.asList(reservation1));

        Order order1 = new Order(LocalDate.parse("2024-04-25"), LocalTime.parse("16:15"), 22, customer2);
        order1.setDishes(Arrays.asList(dish1, dish1));
        orderRepository.saveAll(Arrays.asList(order1));

    }
}
