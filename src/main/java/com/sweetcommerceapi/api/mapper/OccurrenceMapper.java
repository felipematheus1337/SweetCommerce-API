package com.sweetcommerceapi.api.mapper;

import com.sweetcommerceapi.api.model.output.OccurrenceOutput;
import com.sweetcommerceapi.domain.model.Delivery;
import com.sweetcommerceapi.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OccurrenceMapper {

    private ModelMapper modelMapper;

    public OccurrenceOutput entityToOutput(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceOutput.class);
    }

    public Occurrence outputToEntity(OccurrenceOutput occurrenceOutput) {
        return modelMapper.map(occurrenceOutput, Occurrence.class);
    }

    public List<OccurrenceOutput> listEntityToListOutput(List<Occurrence> occurrences) {
        return occurrences.stream()
                .map(this::entityToOutput)
                .collect(Collectors.toList());
    }
}
