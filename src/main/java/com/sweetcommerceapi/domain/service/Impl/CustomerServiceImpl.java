package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Customer;
import com.sweetcommerceapi.domain.repository.CustomerRepository;
import com.sweetcommerceapi.domain.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl  implements CustomerService {

    private CustomerRepository repository;


    @Override
    public Customer search(Long customerId) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Void delete(Long customerId) {
        return null;
    }
}
