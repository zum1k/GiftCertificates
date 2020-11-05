package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class GiftCertificateDto {
    private long giftId;
    private String name;
    private String description;
    private BigDecimal price;
    private OffsetDateTime createDate;
    private OffsetDateTime lastUpdateDate;
    private long duration;
    private List<TagDto> tags;

    public GiftCertificateDto(String name, String description, BigDecimal price,
                              long duration, List<TagDto> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }
}
