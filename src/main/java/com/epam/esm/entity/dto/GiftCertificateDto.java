package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class GiftCertificateDto {
    private long giftId;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate createDate;
    private LocalDate lastUpdateDate;
    private long duration;
    private List<TagDto> tags;

    public GiftCertificateDto(long giftId, String name, String description,
                              BigDecimal price, LocalDate createDate, LocalDate lastUpdateDate,
                              long duration, List<TagDto> tags) {
        this.giftId = giftId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
        this.tags = tags;
    }
}
