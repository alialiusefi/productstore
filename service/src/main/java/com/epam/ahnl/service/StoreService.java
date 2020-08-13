package com.epam.ahnl.service;

import com.epam.ahnl.dto.PageDTO;
import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.CompanyCode;


public interface StoreService {

  StoreDTO addStore(StoreDTO storeDTO);

  StoreDTO getStore(Long storeId);

  PageDTO<StoreDTO> getAllStores(Integer size, Integer page);

  PageDTO<StoreDTO> getAllStoresByCompanyCode(CompanyCode companyCode, Integer size, Integer page);

  StoreDTO getNearestStoreByCompanyCode(Double latitude, Double longitude, CompanyCode code);

  StoreDTO editStore(Long storeId, StoreDTO storeDTO);

  void deleteStore(Long storeId);
}
