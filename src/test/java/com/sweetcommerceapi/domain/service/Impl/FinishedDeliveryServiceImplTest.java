package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Customer;
import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.model.DeliveryStatus;
import com.sweetcommerceapi.domain.model.Recipient;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FinishedDeliveryServiceImplTest {

    private static final Long ID = 1L;

    private static final String CUSTOMER_NAME = "Felipe";
    private static final String CUSTOMER_EMAIL = "felipe@mail.com";

    private static final String CUSTOMER_PHONE = "(21) 98665-7239";

    private static final Integer CUSTOMER_AGE = 25;

    @InjectMocks
    private FinishedDeliveryServiceImpl finishedDeliveryService;

    @Mock
    private SearchDeliveryServiceImpl searchDeliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    private Delivery delivery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startDelivery();
    }

    @Test
    public void finished_validInput_deliveryFinished() {

        //Arrange
        long deliveryId = 1L;
        Delivery mockDelivery = mock(Delivery.class);
        when(searchDeliveryService.search(deliveryId)).thenReturn(mockDelivery);
        doNothing().when(mockDelivery).finish();
        doNothing().when(mockDelivery).setStatus(DeliveryStatus.FINISHED);
        when(deliveryRepository.save(mockDelivery)).thenReturn(mockDelivery);

        //Act
        finishedDeliveryService.finished(deliveryId);

        //Assert
        verify(mockDelivery).finish();
        verify(mockDelivery).setStatus(DeliveryStatus.FINISHED);
        verify(deliveryRepository).save(mockDelivery);
    }



    private void startDelivery() {
        delivery = new Delivery(ID,
                new Customer(ID,CUSTOMER_NAME,CUSTOMER_EMAIL,CUSTOMER_PHONE,CUSTOMER_AGE),
                new Recipient(CUSTOMER_NAME,"Rio de Janeiro","85A","APT603","RJ"),
                DeliveryStatus.PENDING,
                new BigDecimal("200.00"),
                OffsetDateTime.now()
                );
    }
}