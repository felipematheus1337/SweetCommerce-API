package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.exception.EntityNotFoundException;
import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import com.sweetcommerceapi.domain.service.SearchDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SearchDeliveryServiceImpl  implements SearchDeliveryService {

    private DeliveryRepository deliveryRepository;

    @Override
    public Delivery search(long deliveryId) {
        return deliveryRepository
                .findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
    }
}
