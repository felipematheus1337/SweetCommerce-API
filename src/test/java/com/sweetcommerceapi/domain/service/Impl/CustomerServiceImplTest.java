package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.exception.BusinessException;
import com.sweetcommerceapi.domain.model.Customer;
import com.sweetcommerceapi.domain.repository.CustomerRepository;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class CustomerServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Felipe";
    private static final String EMAIL = "felipe@mail.com";

    private static final String PHONE = "(21) 98665-7239";

    private static final Integer AGE = 25;

    private static final String EMAIL_ALREADY_USED = "E-mail already in use!";

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;
    private Optional<Customer> optionalCustomer;



    @BeforeEach
    void setUp() {
        openMocks(this);
        startCustomer();
    }


    @Test
    void whenSearchAnCustomerByIdReturnAnInstanceOfCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);

        Customer response = service.search(ID);

        assertNotNull(response);

        assertEquals(Customer.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }


    @Test
    void whenCreateAnCustomerWithUniqueEmailReturnAnInstanceOfCustomer() {
       when(customerRepository.save(any())).thenReturn(customer);
       boolean emailExists = customerRepository.findByEmail(customer.getEmail()).isPresent();
       assertFalse(emailExists);

       Customer response = service.save(customer);

       assertEquals(response.getClass(), Customer.class);
       assertEquals(EMAIL, customer.getEmail());
       assertEquals(ID, customer.getId());
       assertEquals(NAME, customer.getName());
       assertEquals(PHONE, customer.getPhone());

    }

    @Test
    void whenCreateAnCustomerItAlreadyUsedEmailThrowsException() {
      when(customerRepository.findByEmail(anyString())).thenThrow(new BusinessException(EMAIL_ALREADY_USED));

      try {
          service.save(customer);
      } catch(Exception e) {
          assertEquals(BusinessException.class, e.getClass());
          assertEquals(EMAIL_ALREADY_USED, e.getMessage());
      }

    }

    @Test
    void delete() {
     doNothing().when(customerRepository).deleteById(anyLong());
     service.delete(ID);
     verify(customerRepository, times(1)).deleteById(anyLong());
    }

    private void startCustomer() {
        customer = new Customer(ID,NAME,EMAIL,PHONE,AGE);
        optionalCustomer = Optional.of(new Customer(ID,NAME,EMAIL,PHONE,AGE));

    }
}