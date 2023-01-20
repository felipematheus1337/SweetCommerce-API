package com.sweetcommerceapi.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOutput {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Integer age;
}
