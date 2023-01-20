package com.sweetcommerceapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
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





}
