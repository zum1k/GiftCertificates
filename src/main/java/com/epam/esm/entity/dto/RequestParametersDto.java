package com.epam.esm.entity.dto;

import com.epam.esm.entity.DateSortType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RequestParametersDto {
  @Value("tag_name")
  private String tagName;
  @Value("part_name")
  private String partName;
  @Value("part_description")
  private String partDescription;
  @Value("sort_type")
  private DateSortType type;

  @Size(min = 15, max = 50, message = "page size must not be greater than 50")
  @Value("page_size")
  private Integer pageLimit = 15;

  @Size(min = 1, message = "page size must be positive")
  @Value("page")
  private Integer page = 1;
}
