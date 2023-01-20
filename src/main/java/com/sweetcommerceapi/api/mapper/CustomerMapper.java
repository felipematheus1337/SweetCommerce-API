package com.sweetcommerceapi.api.mapper;


import com.sweetcommerceapi.api.model.output.CustomerOutPut;
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

    public CustomerOutPut toModel(Customer customer) {
        return modelMapper.map(customer, CustomerOutPut.class);
    }

    public List<CustomerOutPut> toCollection(List<Customer> customers) {
         return customers.stream()
                 .map(this::toModel)
                 .collect(Collectors.toList());
    }
}
