package cs489.project.controller;

import cs489.project.dto.order.OrdeResponse;
import cs489.project.dto.order.OrderRequest;
import cs489.project.exception.OrderNotFoundException;
import cs489.project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project/public/order")
public class OrderController {
    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<OrdeResponse>> getOrders(@PathVariable("customerId") int customerId) {
        return ResponseEntity.ok(orderService.getAllOrdersByCustomerId(customerId));
    }

    @PostMapping("/addorder/{customerId}")
    public ResponseEntity<OrdeResponse> addOrder(@PathVariable("customerId") int customerId, @RequestBody OrderRequest req) {
        return ResponseEntity.ok(orderService.addNewOrder(customerId, req));
    }

    @GetMapping("/changestatus/{customerId}/{status}")
    public ResponseEntity<OrdeResponse> changeStatus(@PathVariable("customerId") int customerId, @PathVariable("status") int status) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.changeOrderStatus(customerId, status));
    }
}
