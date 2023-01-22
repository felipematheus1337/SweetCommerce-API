package com.sweetcommerceapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Recipient {

    @Column(name = "recipient_name")
    private String name;

    @Column(name = "recipient_place")
    private String place;

    @Column(name = "recipient_number")
    private String number;

    @Column(name = "recipient_complement")
    private String complement;

    @Column(name = "recipient_district")
    private String district;

    public Recipient(String name, String place, String number, String complement, String district) {
        this.name = name;
        this.place = place;
        this.number = number;
        this.complement = complement;
        this.district = district;
    }
}
