package cs489.project.service.impl;

import cs489.project.dto.customer.CustomerResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.model.Customer;
import cs489.project.repository.CustomerRepository;
import cs489.project.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponse> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(a -> new CustomerResponse(
                        a.getCustomerId(),
                        a.getFirstname(),
                        a.getLastname(),
                        a.getEmail(),
                        a.getPhoneHumber()
                )).toList();
    }

    @Override
    public CustomerResponse getCustomerById(int customerId) throws CustomerNotFoundException {
        Customer cust = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found",
                        customerId)));
        return new CustomerResponse(
                cust.getCustomerId(),
                cust.getFirstname(),
                cust.getLastname(),
                cust.getEmail(),
                cust.getPhoneHumber()
        );
    }
}
