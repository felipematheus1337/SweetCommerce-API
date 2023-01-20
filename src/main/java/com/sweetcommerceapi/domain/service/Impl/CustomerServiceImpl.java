package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.exception.BusinessException;
import com.sweetcommerceapi.domain.model.Customer;
import com.sweetcommerceapi.domain.repository.CustomerRepository;
import com.sweetcommerceapi.domain.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl  implements CustomerService {

    private CustomerRepository repository;


    @Override
    @Transactional
    public Customer search(Long customerId) {
        return repository.findById(customerId).orElse(null);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
      boolean isEmailAlreadyUsed = repository.findByEmail(customer.getEmail())
              .isPresent();

      if(isEmailAlreadyUsed) {
          throw new BusinessException("E-mail already in use!");
      }
      return repository.save(customer);

    }

    @Override
    @Transactional
    public void delete(Long customerId) {
       repository.deleteById(customerId);
    }


}
