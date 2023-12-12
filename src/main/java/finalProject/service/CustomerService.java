package finalProject.service;

import finalProject.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Long saveCustomer(Customer customer);

    Customer findCustomerById(Long cusId);

    void deleteCustomerById(Long cusId);
}
