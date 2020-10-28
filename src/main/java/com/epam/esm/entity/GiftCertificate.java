package com.epam.esm.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiftCertificate {
    private int giftId;
    private String name;
    private String description;
    private BigDecimal price;
    private Date createDate;
    private Date lastUpdateDate;
    private long duration;

    public GiftCertificate(String name, String description, BigDecimal price, Date createDate, Date lastUpdateDate, long duration) {      ;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
    }
}
