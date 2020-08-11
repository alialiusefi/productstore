package com.epam.ahnl.service;

import com.epam.ahnl.entity.CompanyCode;
import com.epam.ahnl.dto.StoreDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface StoreService {

  StoreDTO addStore(StoreDTO storeDTO);

  StoreDTO getStore(Long storeId);

  Page<List<StoreDTO>> getAllStores(Integer size, Integer page);

  Page<List<StoreDTO>> getAllStoresByCompanyCode(CompanyCode companyCode, Integer size, Integer page);

  StoreDTO getNearestStoreByCompanyCode(Double latitude, Double longitude, CompanyCode code);

  StoreDTO editStore(Long storeId, StoreDTO storeDTO);

  void deleteStore(Long storeId);

}
