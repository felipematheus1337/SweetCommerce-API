package com.sweetcommerceapi.api.model.input;

import com.sweetcommerceapi.api.model.output.RecipientOutput;
import com.sweetcommerceapi.domain.model.Recipient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInput {

    @Valid
    @NotNull
    private CustomerIdInput customer;

    @Valid
    @NotNull
    private RecipientInput recipientInput;

    private BigDecimal tax;
}
