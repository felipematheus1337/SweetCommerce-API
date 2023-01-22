package com.sweetcommerceapi.api.controller;

import com.sweetcommerceapi.api.mapper.OccurrenceMapper;
import com.sweetcommerceapi.api.model.input.OccurrenceInput;
import com.sweetcommerceapi.api.model.output.OccurrenceOutput;
import com.sweetcommerceapi.domain.service.Impl.CustomerServiceImpl;
import com.sweetcommerceapi.domain.service.Impl.RegisterOccurrenceServiceImpl;
import com.sweetcommerceapi.domain.service.Impl.SearchDeliveryServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery/{deliveryId}/occurrences")
public class OccurrenceController {

    private CustomerServiceImpl customerService;
    private SearchDeliveryServiceImpl searchDeliveryService;

    private RegisterOccurrenceServiceImpl registerOccurrenceService;

    private OccurrenceMapper occurrenceMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceOutput register(@PathVariable Long deliveryId,
                                     @Valid @RequestBody OccurrenceInput occurrenceInput) {
        var occurrenceRegistered = registerOccurrenceService.register(deliveryId, occurrenceInput.getDescription());
        return occurrenceMapper.entityToOutput(occurrenceRegistered);
    }

    @GetMapping
    public List<OccurrenceOutput> listAll(@PathVariable Long deliveryId) {
    var delivery = searchDeliveryService.search(deliveryId);
    return occurrenceMapper.listEntityToListOutput(delivery.getOccurrences());
    }


}
