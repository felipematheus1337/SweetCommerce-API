package com.sweetcommerceapi.api.controller;

import com.sweetcommerceapi.api.mapper.DeliveryMapper;
import com.sweetcommerceapi.api.model.input.DeliveryInput;
import com.sweetcommerceapi.api.model.output.DeliveryOutput;
import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.repository.DeliveryRepository;
import com.sweetcommerceapi.domain.service.Impl.FinishedDeliveryServiceImpl;
import com.sweetcommerceapi.domain.service.Impl.RequestDeliveryServiceImpl;
import com.sweetcommerceapi.domain.service.Impl.SearchDeliveryServiceImpl;
import com.sweetcommerceapi.domain.service.RequestDeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private DeliveryRepository deliveryRepository;
    private RequestDeliveryServiceImpl requestDeliveryService;
    private FinishedDeliveryServiceImpl finishedDeliveryService;
    private DeliveryMapper deliveryMapper;

    private SearchDeliveryServiceImpl searchDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryOutput request(@Valid @RequestBody DeliveryInput deliveryInput) {
     var newDelivery = deliveryMapper.inputToEntity(deliveryInput);
     return deliveryMapper.entityToOutput(requestDeliveryService.request(newDelivery));
    }

    @GetMapping
    public List<DeliveryOutput> listAll() {
     return deliveryMapper.listEntityToListOutput(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryOutput> searchAnDelivery(@PathVariable Long deliveryId) {
       var deliverySearched = searchDeliveryService.search(deliveryId);

       if(deliverySearched != null) {
           return ResponseEntity.ok(deliveryMapper.entityToOutput(deliverySearched));
       }

       return ResponseEntity.notFound().build();

    }

    @PutMapping("/{deliveryId}/finish")
    public void finishAnDelivery(@PathVariable Long deliveryId) {
     finishedDeliveryService.finished(deliveryId);
    }
}
