package com.epam.ahnl.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class GeoLocationDTO {

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

}
