package cs489.project.service;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.model.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(int id) throws CustomerNotFoundException;

}
