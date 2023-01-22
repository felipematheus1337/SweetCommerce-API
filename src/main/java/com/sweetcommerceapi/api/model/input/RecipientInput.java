package com.sweetcommerceapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecipientInput {

    @NotBlank
    private String name;

    @NotBlank
    private String place;

    @NotBlank
    private String number;

    @NotBlank
    private String complement;

    @NotBlank
    private String district;
}
