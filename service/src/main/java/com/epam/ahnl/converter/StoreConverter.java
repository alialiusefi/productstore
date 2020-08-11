package com.epam.ahnl.converter;

import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.Store;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter extends BaseConverter<StoreDTO, Store> {

  protected StoreConverter(ModelMapper modelMapper) {
    super(modelMapper);
  }

  @Override
  public Store toEntity(StoreDTO dto) {
   return modelMapper.map(dto, Store.class);
  }

  @Override
  public StoreDTO toDTO(Store entity) {
    return modelMapper.map(entity,StoreDTO.class);
  }
}
