package com.epam.ahnl.service;

import com.epam.ahnl.dto.PageDTO;
import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.CompanyCode;
import javax.validation.constraints.Min;


public interface StoreService {

  StoreDTO addStore(StoreDTO storeDTO);

  StoreDTO getStore(Long storeId);

  PageDTO<StoreDTO> getAllStores(@Min(1) Integer size, @Min(1) Integer page);

  PageDTO<StoreDTO> getAllStoresByCompanyCode(
      CompanyCode companyCode, @Min(1) Integer size, @Min(1) Integer page);

  StoreDTO getNearestStoreByCompanyCode(Double latitude, Double longitude, CompanyCode code);

  StoreDTO editStore(Long storeId, StoreDTO storeDTO);

  void deleteStore(Long storeId);
}
