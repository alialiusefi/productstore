package com.epam.ahnl.dto;

import com.epam.ahnl.entity.CompanyCode;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class StoreDTO {

  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String phone;

  @Valid
  @NotNull
  private AddressDTO address;

  @Valid
  @NotNull
  private GeoLocationDTO geoLocation;

  @NotNull
  private CompanyCode companyCode;
}
