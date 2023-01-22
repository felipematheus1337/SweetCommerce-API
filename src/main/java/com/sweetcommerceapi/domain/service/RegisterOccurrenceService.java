package com.sweetcommerceapi.domain.service;

import com.sweetcommerceapi.domain.model.Occurrence;

public interface RegisterOccurrenceService {

    Occurrence register(Long deliveryId, String description);


}
