package com.epam.ahnl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationDTO {

  private Double latitude;

  private Double longitude;

}
