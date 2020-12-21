package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
  private long orderId;

  @NotNull()
  @Min(value = 0, message = "Value can't be less than 0")
  private long userId;

  private BigDecimal price;
  private ZonedDateTime purchaseDate;
  private ZonedDateTime createDate;
  private ZonedDateTime lastUpdateDate;
  private List<GiftCertificateDto> gifts;
}
