package com.epam.ahnl.converter;

import com.epam.ahnl.dto.GeoLocationDTO;
import com.epam.ahnl.entity.GeoLocation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GeoLocationConverter extends BaseConverter<GeoLocationDTO, GeoLocation> {

  protected GeoLocationConverter(ModelMapper modelMapper) {
    super(modelMapper);
  }

  @Override
  public GeoLocation toEntity(GeoLocationDTO dto) {
    return modelMapper.map(dto, GeoLocation.class);
  }

  @Override
  public GeoLocationDTO toDTO(GeoLocation entity) {
    return modelMapper.map(entity, GeoLocationDTO.class);
  }
}
