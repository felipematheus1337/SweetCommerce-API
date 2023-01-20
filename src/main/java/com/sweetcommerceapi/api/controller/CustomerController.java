package com.sweetcommerceapi.api.controller;

import com.sweetcommerceapi.domain.repository.CustomerRepository;
import com.sweetcommerceapi.domain.service.Impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerServiceImpl customerService;
   private CustomerRepository customerRepository;


}
