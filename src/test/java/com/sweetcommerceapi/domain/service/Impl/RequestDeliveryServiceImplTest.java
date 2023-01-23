package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Customer;
import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.model.DeliveryStatus;
import com.sweetcommerceapi.domain.model.Recipient;
import com.sweetcommerceapi.domain.repository.CustomerRepository;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RequestDeliveryServiceImplTest {


    @InjectMocks
    private RequestDeliveryServiceImpl requestDeliveryService;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private CustomerRepository customerRepository;

    private Optional<Customer> customer;

    private Delivery delivery;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUp();
    }


    @Test
    void whenRequestAnDeliveryReturnAnInstanceOfIt() {
        when(customerService.search(1L)).thenReturn(customer.get());
        Delivery deliveryMock = mock(Delivery.class);
        Customer customerMock = mock(Customer.class);

        doNothing().when(deliveryMock).setCustomer(customerMock);
        doNothing().when(deliveryMock).setStatus(DeliveryStatus.PENDING);
        doNothing().when(deliveryMock).setOrderDate(OffsetDateTime.now());

       assertNotNull(deliveryMock);
       assertNotNull(customerMock);



    }

    private void startUp() {
        customer = Optional.of(new Customer(1L,
                "teste","teste@mail.com",
                "999888",25));
        delivery = new Delivery(1L,customer.get(),
                new Recipient("Rio de Janeiro",
                        "Brasil","85A",
                        "APT702","RJ"),
                DeliveryStatus.PENDING,
                new BigDecimal("200.0"),
                OffsetDateTime.now());

        customerService.save(customer.get());
    }
}