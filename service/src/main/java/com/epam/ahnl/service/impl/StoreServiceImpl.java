package com.epam.ahnl.service.impl;

import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.CompanyCode;
import com.epam.ahnl.entity.Store;
import com.epam.ahnl.repository.StoreRepository;
import com.epam.ahnl.service.StoreService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;
  private final ModelMapper modelMapper;

  @Transactional
  @Override
  public StoreDTO addStore(StoreDTO storeDTO) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public StoreDTO getStore(Long storeId) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public Page<List<StoreDTO>> getAllStores(Integer size, Integer page) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public Page<List<StoreDTO>> getAllStoresByCompanyCode(
      CompanyCode companyCode, Integer size, Integer page) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public StoreDTO getNearestStoreByCompanyCode(
      Double latitude, Double longitude, CompanyCode code) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Transactional
  @Override
  public StoreDTO editStore(Long storeId, StoreDTO storeDTO) {
    throw new UnsupportedOperationException("Not implemented yet!");

  }

  @Override
  public void deleteStore(Long storeId) {
    throw new UnsupportedOperationException("Not implemented yet!");

  }

  private Store toEntity(StoreDTO dto) {
    throw new UnsupportedOperationException("Not implemented yet!");

  }

  private StoreDTO toDTO(Store store) {
    throw new UnsupportedOperationException("Not implemented yet!");

  }
}
