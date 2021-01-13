package com.epam.esm.entity.dto;

import com.epam.esm.entity.DateSortType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RequestParametersDto {
  private String tagName;
  private String partName;
  private String partDescription;
  private DateSortType type;

  @Size(min = 15, max = 50, message = "page size must not be greater than 50")
  private Integer pageLimit = 15;

  @Size(min = 1, message = "page size must be positive")
  private Integer page = 1;
}
