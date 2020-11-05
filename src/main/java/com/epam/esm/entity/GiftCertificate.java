package com.epam.esm.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

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
    private OffsetDateTime createDate;
    private OffsetDateTime lastUpdateDate;
    private long duration;

    public GiftCertificate(String name, String description, BigDecimal price,
                           OffsetDateTime createDate, OffsetDateTime lastUpdateDate, long duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
    }

}
