package finalProject.service.Impl;

import finalProject.domain.Customer;
import finalProject.entity.CustomerEntity;
import finalProject.exception.CustomerNotFoundException;
import finalProject.mapper.CustomerMapperHelper;
import finalProject.repository.CustomerRepository;
import finalProject.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapperHelper customerMapperHelper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapperHelper customerMapperHelper) {
        this.customerRepository = customerRepository;
        this.customerMapperHelper = customerMapperHelper;
    }


    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerMapperHelper.convertCustomerEntityListToCustomerList (customerEntities);
    }

    @Override
    public Long saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapperHelper.convertCustomerToCustomerEntity(customer);
        CustomerEntity savedCus =  customerRepository.save(customerEntity);
        return savedCus.getId();
    }

    @Override
    public Customer findCustomerById(Long cusId) {
        Optional<CustomerEntity> byId=customerRepository.findById(cusId);
        if(byId.isPresent()){
            CustomerEntity foundCus = byId.get();
            return customerMapperHelper.convertCustomerEntityToCustomer(foundCus);
        }
        throw new CustomerNotFoundException("There is no customer by id " + cusId);
    }

    @Override
    public void deleteCustomerById(Long cusId) {
        customerRepository.deleteById(cusId);
    }
}
