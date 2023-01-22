package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.model.DeliveryStatus;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import com.sweetcommerceapi.domain.service.RequestDeliveryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class RequestDeliveryServiceImpl implements RequestDeliveryService {

    private CustomerServiceImpl customerService;

    private DeliveryRepository deliveryRepository;


    @Transactional
    @Override
    public Delivery request(Delivery delivery) {
        var client = customerService.search(delivery.getCustomer().getId());
        delivery.setCustomer(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }


}
