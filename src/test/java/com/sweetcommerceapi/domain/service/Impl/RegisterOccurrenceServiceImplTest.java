package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.model.Occurrence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterOccurrenceServiceImplTest {

    private static final Long DELIVERY_ID = 1L;
    private static final String DESCRIPTION = "Occurrence test!";

    @InjectMocks
    private RegisterOccurrenceServiceImpl registerOccurrenceService;

    @Mock
    private SearchDeliveryServiceImpl searchDeliveryService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void whenRegisterAnOccurrenceReturnAnInstanceOfIt() {
        long deliveryId = 1L;
        Delivery mockDelivery = mock(Delivery.class);
        Occurrence mockOccurence = mock(Occurrence.class);

        when(searchDeliveryService.search(deliveryId)).thenReturn(mockDelivery);
        when(mockDelivery.addOccurrence(DESCRIPTION)).thenReturn(mockOccurence);

        var occurrence = registerOccurrenceService.register(deliveryId,DESCRIPTION);

        assertNotNull(occurrence);
        assertEquals(occurrence.getClass(), mockOccurence.getClass());

    }
}