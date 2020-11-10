package com.epam.esm.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiftCertificate {
    private int certificateId;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate createDate;
    private LocalDate lastUpdateDate;
    private long duration;

    public GiftCertificate(String name, String description, BigDecimal price,
                           LocalDate createDate, LocalDate lastUpdateDate, long duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
    }
}
