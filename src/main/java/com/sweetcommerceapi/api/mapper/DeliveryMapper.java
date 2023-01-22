package com.sweetcommerceapi.api.mapper;

import com.sweetcommerceapi.api.model.input.DeliveryInput;
import com.sweetcommerceapi.api.model.output.DeliveryOutput;
import com.sweetcommerceapi.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DeliveryMapper {

    private ModelMapper modelMapper;


    public Delivery inputToEntity(DeliveryInput deliveryInput) {
        return modelMapper.map(deliveryInput, Delivery.class);
    }

    public DeliveryOutput entityToOutput(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryOutput.class);
    }

    public List<DeliveryOutput> listEntityToListOutput(List<Delivery> deliveryList) {
        return deliveryList.stream().map(this::entityToOutput).collect(Collectors.toList());
    }
}
