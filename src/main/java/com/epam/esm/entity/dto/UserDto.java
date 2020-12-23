package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {
  private long userId;

  @NotNull(message = "Need to enter email")
  @Size(min = 15, max = 45, message = "Email size must be in 13 to 45 symbols range")
  private String email;

  @NotNull(message = "Need to enter password")
  @Size(min = 9, max = 23, message = "Password size must be in 9 to 23 symbols range")
  private String password;

  @NotNull(message = "Need to enter first name")
  @Size(min = 2, max = 45, message = " First name size must be in 2 to 45 symbols range")
  private String firstName;

  @NotNull(message = "Need to enter last name")
  @Size(min = 3, max = 45, message = "Last name size must be in 2 to 45 symbols range")
  private String lastName;

  private ZonedDateTime createDate;

  private ZonedDateTime lastUpdateDate;

  private Set<OrderDto> orders = new HashSet<>();
}
