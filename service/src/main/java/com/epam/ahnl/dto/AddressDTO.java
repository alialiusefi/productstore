package com.epam.ahnl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

  private String street;

  private Integer houseNumber;

  private String postalCode;

  private String city;

  private String countryCode;

}
