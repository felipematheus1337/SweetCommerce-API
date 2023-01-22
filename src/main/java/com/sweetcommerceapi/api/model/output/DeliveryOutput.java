package com.sweetcommerceapi.api.model.output;


import com.sweetcommerceapi.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryOutput {

    private Long id;

    private CustomerOutput customerOutput;

   private RecipientOutput recipientOutput;

   private DeliveryStatus deliveryStatus;

   private BigDecimal tax;

   private OffsetDateTime orderDate;

   private OffsetDateTime dateFinished;


}
