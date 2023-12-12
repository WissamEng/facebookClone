package finalProject.mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import finalProject.domain.Customer;
import finalProject.dto.CustomerDTO;
import finalProject.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapperHelper {
    private final ObjectMapper mapper;

    @Autowired
    public CustomerMapperHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Customer> convertCustomerEntityListToCustomerList(List<CustomerEntity> customerEntities){
        List<Customer> customers = new ArrayList<>();
        for(CustomerEntity customerEntity: customerEntities){
            customers.add(mapper.convertValue(customerEntity, Customer.class));
        }

        return customers;
    }

    public List<CustomerDTO> convertCustomerListToCustomerDTOList(List<Customer> customers){
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for(Customer customer: customers){
            customerDTOs.add(mapper.convertValue(customer, CustomerDTO.class));
        }

        return customerDTOs;
    }

    public CustomerEntity convertCustomerToCustomerEntity(Customer customer) {
        return mapper.convertValue(customer, CustomerEntity.class);
    }

    public Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        return mapper.convertValue(customerDTO, Customer.class);
    }

    public Customer convertCustomerEntityToCustomer(CustomerEntity customerEntity){
        return mapper.convertValue(customerEntity, Customer.class);
    }
}
