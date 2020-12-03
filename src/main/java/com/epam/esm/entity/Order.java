package com.epam.esm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityListeners;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@EntityListeners({AuditDateEntityListener.class})
@Data
@AllArgsConstructor
public class Order implements AuditEntity {
    private long orderId;
    private List<GiftCertificate> giftCertificates;
    private BigDecimal price;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;
    private ZonedDateTime purchaseDate;

}
