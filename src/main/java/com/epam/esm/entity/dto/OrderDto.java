package com.epam.esm.entity.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDto {
    private long orderId;
    @NotNull()
    @Min(value = 1, message = "Value can't be less than 1")
    private long userId;
    @NotNull
    @Min(value = 1, message = "Need at least 1 certificate")
    private List<GiftCertificateDto> dtos;
    private BigDecimal price;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;
}
