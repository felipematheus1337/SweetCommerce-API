package com.sweetcommerceapi.domain.service;

import com.sweetcommerceapi.domain.model.Customer;

public interface CustomerService {

    Customer search(Long customerId);

    Customer save(Customer customer);

    void delete(Long customerId);
}
