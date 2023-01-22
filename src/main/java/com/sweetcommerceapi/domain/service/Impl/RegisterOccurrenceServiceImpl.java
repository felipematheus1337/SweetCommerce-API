package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.Occurrence;
import com.sweetcommerceapi.domain.service.RegisterOccurrenceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterOccurrenceServiceImpl  implements RegisterOccurrenceService {

    private SearchDeliveryServiceImpl searchDeliveryService;


    @Override
    public Occurrence register(Long deliveryId, String description) {
        var delivery = searchDeliveryService.search(deliveryId);
        return delivery.addOccurrence(description);
    }
}
