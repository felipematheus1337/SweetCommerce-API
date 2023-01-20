package com.sweetcommerceapi.api.model.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInput {

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone;


    @Positive
    private Integer age;
}
