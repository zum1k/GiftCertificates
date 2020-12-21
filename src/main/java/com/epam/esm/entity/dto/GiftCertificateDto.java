package com.epam.esm.entity.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class GiftCertificateDto {
  private long giftId;

  @NotNull(message = "Need to enter name")
  @Size(min = 2, max = 45, message = "Name size must be in 2 to 45 sumbols range")
  private String name;

  @NotNull(message = "Need to enter description")
  @Size(min = 2, max = 45, message = "Description size must be in 2 to 45 symbols range")
  private String description;

  @NotNull(message = "Need to enter price")
  @Min(value = 1, message = "Price can\'t be less than \'1\'")
  private BigDecimal price;

  private ZonedDateTime createDate;
  private ZonedDateTime lastUpdateDate;

  @NotNull
  @Min(1)
  private long duration;

  private List<TagDto> tags;

  public GiftCertificateDto(
      String name, String description, BigDecimal price, long duration, List<TagDto> tags) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.duration = duration;
    this.tags = tags;
  }

  public GiftCertificateDto() {}
}
