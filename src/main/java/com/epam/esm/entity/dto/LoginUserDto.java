package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class LoginUserDto {
  @NotNull(message = "Need to enter email")
  @Size(min = 5, max = 45, message = "Email size must be in 13 to 45 symbols range")
  private String email;

  @NotNull(message = "Need to enter password")
  @Size(min = 3, max = 23, message = "Password size must be in 9 to 23 symbols range")
  @ToString.Exclude
  private String password;
}
