package com.sweetcommerceapi.domain.service;

import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



public interface SearchDeliveryService {

    Delivery search(long deliveryId);
}
