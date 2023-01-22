package com.sweetcommerceapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OccurrenceInput {

    @NotBlank
    private String description;
}
