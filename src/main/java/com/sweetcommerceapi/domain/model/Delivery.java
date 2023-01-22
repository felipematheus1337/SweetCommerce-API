package com.sweetcommerceapi.domain.model;

import com.sweetcommerceapi.domain.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Customer customer;

    @Embedded
    private Recipient recipient;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private BigDecimal tax;

    private OffsetDateTime orderDate;

    private OffsetDateTime dateFinished;

    public Delivery(Long id, Customer customer, Recipient recipient, DeliveryStatus status, BigDecimal tax, OffsetDateTime orderDate) {
        this.id = id;
        this.customer = customer;
        this.recipient = recipient;
        this.status = status;
        this.tax = tax;
        this.orderDate = orderDate;
    }


    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setRegisterDate(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOccurrences().add(occurrence);

        return occurrence;
    }

    public void finish() {
        if(canNotBeFinished()) {
            throw new BusinessException("Delivery can't be finished");
        }

        setStatus(DeliveryStatus.FINISHED);
        setDateFinished(OffsetDateTime.now());
    }




    public boolean canBeFinished() {
        return DeliveryStatus.PENDING.equals(getStatus());
    }

    public boolean canNotBeFinished() {
     return !canBeFinished();
    }



}
