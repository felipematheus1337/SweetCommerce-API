package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Customer;
import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.model.DeliveryStatus;
import com.sweetcommerceapi.domain.model.Recipient;
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
import static org.mockito.Mockito.when;

class SearchDeliveryServiceImplTest {


    @InjectMocks
    private SearchDeliveryServiceImpl searchDeliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    private static final Long CUSTOMER_ID = 1L;
    private static final Customer costumer = new Customer();

    private static final String CUSTOMER_NAME = "Felipe";
    private static final String CUSTOMER_EMAIL = "felipe@mail.com";

    private static final String CUSTOMER_PHONE = "(21) 98665-7239";

    private static final Integer CUSTOMER_AGE = 25;

    private Delivery delivery;






    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startDelivery();
    }

    @Test
    void searchAnDeliveryAndReturnAnInstanceOfIt() {
        when(deliveryRepository.findById(anyLong())).thenReturn(Optional.of(delivery));
        var response = searchDeliveryService.search(CUSTOMER_ID);

        assertNotNull(response);

        assertEquals(response.getStatus(),delivery.getStatus());
        assertEquals(response.getId(),delivery.getId());
        assertEquals(response.getCustomer(),delivery.getCustomer());
    }

    private void startDelivery() {
        delivery = new Delivery(1L,
                new Customer(CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_EMAIL,CUSTOMER_PHONE,CUSTOMER_AGE),
                new Recipient(CUSTOMER_NAME,"Rio de Janeiro","85A","APT603","RJ"),
                DeliveryStatus.PENDING,
                new BigDecimal("200.00"),
                OffsetDateTime.now()
        );
    }
}