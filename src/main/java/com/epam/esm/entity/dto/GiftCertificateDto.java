package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
//TODO need validation
public class GiftCertificateDto {

    private long giftId;
    @NotNull
    @Size(min = 2, max = 45)
    private String name;
    @NotNull
    @Size(min = 2, max = 45)
    private String description;
    @NotNull
    @Min(1)
    private BigDecimal price;
    private LocalDate createDate;
    private LocalDate lastUpdateDate;
    @NotNull
    @Min(1)
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
