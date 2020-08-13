package com.epam.ahnl.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class AddressDTO {

  @Length(max = 256)
  @NotBlank
  private String street;

  @Positive
  private Integer houseNumber;

  @Length(max = 10)
  @NotBlank
  private String postalCode;

  @Length(max = 256)
  @NotBlank
  private String city;

  @Length(max = 2)
  @NotBlank
  private String countryCode;

}
