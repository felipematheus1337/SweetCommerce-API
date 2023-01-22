package com.sweetcommerceapi.api.model.input;

import jakarta.validation.constraints.NotBlank;

public class OccurrenceInput {

    @NotBlank
    private String description;
}
