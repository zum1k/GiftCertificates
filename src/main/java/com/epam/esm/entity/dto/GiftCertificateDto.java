package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class GiftCertificateDto {
    private int giftId;
    private String name;
    private String description;
    private BigDecimal price;
    private Date createDate;
    private Date lastUpdateDate;
    private long duration;

    private List<TagDto> tags;
}
