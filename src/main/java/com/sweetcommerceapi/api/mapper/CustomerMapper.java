package com.sweetcommerceapi.api.mapper;


import com.sweetcommerceapi.api.model.input.CustomerInput;
import com.sweetcommerceapi.api.model.output.CustomerOutput;
import com.sweetcommerceapi.domain.model.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CustomerMapper {

    private ModelMapper modelMapper;

    public CustomerOutput toModel(Customer customer) {
        return modelMapper.map(customer, CustomerOutput.class);
    }

    public List<CustomerOutput> toCollection(List<Customer> customers) {
         return customers.stream()
                 .map(this::toModel)
                 .collect(Collectors.toList());
    }

    public Customer toEntity(CustomerOutput customer) {
        return modelMapper.map(customer,Customer.class);
    }

    public CustomerInput entityToModel(Customer customer) {
        return modelMapper.map(customer, CustomerInput.class);
    }

    public Customer inputModelToEntity(CustomerInput customerInput) {
        return modelMapper.map(customerInput, Customer.class);
    }
}
