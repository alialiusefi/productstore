package com.epam.ahnl.dto;

import com.epam.ahnl.entity.CompanyCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

  // read only annotation here validation
  private Long id;

  private String name;

  private String phone;

  private AddressDTO address;

  private GeoLocationDTO geoLocation;

  private CompanyCode companyCode;
}
