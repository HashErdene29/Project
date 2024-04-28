package cs489.project;

import cs489.project.controller.CustomerController;
import cs489.project.dto.customer.CustomerResponse;
import cs489.project.exception.CustomerNotFoundException;
import cs489.project.model.Customer;
import cs489.project.repository.CustomerRepository;
import cs489.project.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerController customerController;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void findCustomersById() throws CustomerNotFoundException {
        int customerId = 45;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        CustomerResponse customerFound = customerService.getCustomerById(customerId);

        assertNotNull(customerFound);
        Assert.assertEquals(customerId, customerFound.customerId());
    }

    @Test
    public void findAllCustomersByTest(){
        CustomerResponse customer1 = new CustomerResponse(1, "John", "Doe", "john@doe.com", "123-456-7890");
        CustomerResponse customer2 = new CustomerResponse(2, "Janet", "Akar", "janet@akar.com", "234-456-7890");

        List<CustomerResponse> customers = Arrays.asList(customer1, customer2);
        ResponseEntity<List<CustomerResponse>> response = ResponseEntity.ok(customers);
        when(customerController.getAllCustomers()).thenReturn(response);

        ResponseEntity<List<CustomerResponse>> response1 = customerController.getAllCustomers();
        Assert.assertEquals(customers, response1.getBody());

    }
}
