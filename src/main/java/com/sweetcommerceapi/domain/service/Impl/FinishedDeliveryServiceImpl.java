package com.sweetcommerceapi.domain.service.Impl;

import com.sweetcommerceapi.domain.model.DeliveryStatus;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import com.sweetcommerceapi.domain.service.FinishedDeliveryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class FinishedDeliveryServiceImpl    implements FinishedDeliveryService {

    private SearchDeliveryServiceImpl searchDeliveryService;
    private DeliveryRepository deliveryRepository;


    @Transactional
    @Override
    public void finished(long deliveryId) {
        var delivery = searchDeliveryService.search(deliveryId);

        delivery.finish();
        delivery.setStatus(DeliveryStatus.FINISHED);

        deliveryRepository.save(delivery);


    }
}
