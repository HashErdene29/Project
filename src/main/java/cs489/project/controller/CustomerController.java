package cs489.project.controller;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int id) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}
