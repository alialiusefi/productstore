package com.epam.ahnl.converter;

import com.epam.ahnl.dto.AddressDTO;
import com.epam.ahnl.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter extends BaseConverter<AddressDTO, Address> {

  protected AddressConverter(ModelMapper modelMapper) {
    super(modelMapper);
  }

  @Override
  public Address toEntity(AddressDTO dto) {
    return modelMapper.map(dto, Address.class);
  }

  @Override
  public AddressDTO toDTO(Address entity) {
    return modelMapper.map(entity, AddressDTO.class);
  }
}
