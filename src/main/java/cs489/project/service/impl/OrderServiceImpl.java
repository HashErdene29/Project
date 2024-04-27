package cs489.project.service.impl;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.dto.dish.DishDto;
import cs489.project.dto.dish.DishResponse;
import cs489.project.dto.order.OrdeResponse;
import cs489.project.dto.order.OrderRequest;
import cs489.project.exception.OrderNotFoundException;
import cs489.project.model.Customer;
import cs489.project.model.Dish;
import cs489.project.model.Order;
import cs489.project.model.OrderStatus;
import cs489.project.repository.CustomerRepository;
import cs489.project.repository.DishRepository;
import cs489.project.repository.OrderRepository;
import cs489.project.repository.UserRepository;
import cs489.project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private DishRepository dishRepository;
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<OrdeResponse> getAllOrdersByCustomerId(int customerId){
        List<Order> orders = orderRepository.findAllByCustomer_CustomerId(customerId);
        return orders.stream()
                .map(a -> new OrdeResponse(
                        a.getOrderId(),
                        a.getPickupDate(),
                        a.getPickupTime(),
                        a.getTotalPrice(),
                        a.getStatus(),
                        (a.getDishes().stream()
                                .map(b -> new DishDto(
                                        b.getDishId(),
                                        b.getDishName(),
                                        b.getPrice(),
                                        b.getDescription()
                                )).toList()),
                        (new CustomerResponse(
                                a.getCustomer().getCustomerId(),
                                a.getCustomer().getFirstname(),
                                a.getCustomer().getLastname(),
                                a.getCustomer().getEmail(),
                                a.getCustomer().getPhoneHumber()
                        ))
                )).toList();
    }

    @Override
    public OrdeResponse addNewOrder(int customerId, OrderRequest req) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Order order = new Order(
                LocalDate.parse(req.pickupDate()),
                LocalTime.parse(req.pickupTime()),
                req.totalPrice(),
                customer
        );
        List<Dish> dishes = new ArrayList<>();
        for (DishResponse dishResponse : req.dishes()) {
            Dish dish = dishRepository.findById(dishResponse.id()).orElse(null);
            if (dish != null) {
                dishes.add(dish);
            } else {
                // Handle dish not found error
                return null;
            }
        }
        order.setDishes(dishes);
        Order res = orderRepository.save(order);
        return new OrdeResponse(
                res.getOrderId(),
                res.getPickupDate(),
                res.getPickupTime(),
                res.getTotalPrice(),
                res.getStatus(),
                (res.getDishes().stream()
                        .map(a -> new DishDto(
                                a.getDishId(),
                                a.getDishName(),
                                a.getPrice(),
                                a.getDescription()
                        )).toList()),
                (new CustomerResponse(
                        res.getCustomer().getCustomerId(),
                        res.getCustomer().getFirstname(),
                        res.getCustomer().getLastname(),
                        res.getCustomer().getEmail(),
                        res.getCustomer().getPhoneHumber()
                ))
        );
    }

    @Override
    public OrdeResponse changeOrderStatus(int orderId, int newStatus) throws OrderNotFoundException{
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Error: Order with Id, %d, is not found", orderId)));
        OrderStatus status;
        switch (newStatus) {
            case 0:
                status = OrderStatus.PENDING;
                break;
            case 1:
                status = OrderStatus.CONFIRMED;
                break;
            case 2:
                status = OrderStatus.IN_PROGRESS;
                break;
            case 3:
                status = OrderStatus.COMPLETED;
                break;
            case 4:
                status = OrderStatus.CANCELLED;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + newStatus);
        }
        order.setStatus(status);
        Order res = orderRepository.save(order);
        return new OrdeResponse(
                res.getOrderId(),
                res.getPickupDate(),
                res.getPickupTime(),
                res.getTotalPrice(),
                res.getStatus(),
                (res.getDishes().stream()
                        .map(a -> new DishDto(
                                a.getDishId(),
                                a.getDishName(),
                                a.getPrice(),
                                a.getDescription()
                        )).toList()),
                (new CustomerResponse(
                        res.getCustomer().getCustomerId(),
                        res.getCustomer().getFirstname(),
                        res.getCustomer().getLastname(),
                        res.getCustomer().getEmail(),
                        res.getCustomer().getPhoneHumber()
                ))
        );
    }


}
