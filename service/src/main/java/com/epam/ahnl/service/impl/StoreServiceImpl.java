package com.epam.ahnl.service.impl;

import com.epam.ahnl.converter.AddressConverter;
import com.epam.ahnl.converter.GeoLocationConverter;
import com.epam.ahnl.converter.StoreConverter;
import com.epam.ahnl.dto.PageDTO;
import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.Address;
import com.epam.ahnl.entity.CompanyCode;
import com.epam.ahnl.entity.GeoLocation;
import com.epam.ahnl.entity.Store;
import com.epam.ahnl.exception.ResourceNotFoundException;
import com.epam.ahnl.repository.AddressRepository;
import com.epam.ahnl.repository.GeoLocationRepository;
import com.epam.ahnl.repository.StoreRepository;
import com.epam.ahnl.service.StoreService;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private static final String RESOURCE_NOTFOUND_MESSAGE =
      "The resource that has been requested does not exist.";

  private final StoreRepository storeRepository;
  private final AddressRepository addressRepository;
  private final GeoLocationRepository geoLocationRepository;

  private final GeoLocationConverter geoLocationConverter;
  private final StoreConverter storeConverter;
  private final AddressConverter addressConverter;

  @Override
  public StoreDTO getStore(Long storeId) {
    Store store =
        storeRepository
            .findById(storeId)
            .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOTFOUND_MESSAGE));
    return storeConverter.toDTO(store);
  }

  @Override
  public PageDTO<StoreDTO> getAllStores(Integer size, Integer page) {
    Page<Store> pageObj = storeRepository.findAll(PageRequest.of(page - 1, size));

    List storeDTOS = pageObj.getContent()
        .stream()
        .map(storeConverter::toDTO)
        .collect(Collectors.toList());

    return PageDTO.builder()
        .results(storeDTOS)
        .totalResults(pageObj.getTotalElements())
        .build();
  }

  @Override
  public PageDTO<StoreDTO> getAllStoresByCompanyCode(
      CompanyCode companyCode, Integer size, Integer page) {
    Page<Store> pageObj =
        storeRepository.findAllByCompanyCode(companyCode, PageRequest.of(page - 1, size));

    List stores = pageObj.getContent().stream().map(storeConverter::toDTO).collect(Collectors.toList());

    return PageDTO.builder()
        .results(stores)
        .totalResults(pageObj.getTotalElements())
        .build();
  }

  //todo: optional getNearestStore
  @Override
  public StoreDTO getNearestStoreByCompanyCode(
      Double latitude, Double longitude, CompanyCode code) {
    Page<Store> stores = storeRepository.findAllByCompanyCode(code, Pageable.unpaged());
    Store nearestStore = getNearestStore(latitude, longitude, stores);
    return storeConverter.toDTO(nearestStore);
  }

  @Transactional
  @Override
  public StoreDTO addStore(StoreDTO storeDTO) {
    Address address = addressConverter.toEntity(storeDTO.getAddress());
    address = addressRepository.save(address);

    GeoLocation geoLocation = geoLocationConverter.toEntity(storeDTO.getGeoLocation());
    geoLocation = geoLocationRepository.save(geoLocation);

    Store store = storeConverter.toEntity(storeDTO);

    store.setAddress(address);
    store.setGeoLocation(geoLocation);

    store = storeRepository.save(store);

    return storeConverter.toDTO(store);
  }

  @Transactional
  @Override
  public StoreDTO editStore(Long storeId, StoreDTO newStoreDTO) {
    StoreDTO dto = getStore(storeId);

    Address address = addressConverter.toEntity(newStoreDTO.getAddress());
    address = addressRepository.save(address);

    GeoLocation geoLocation = geoLocationConverter.toEntity(newStoreDTO.getGeoLocation());
    geoLocation = geoLocationRepository.save(geoLocation);

    Store store = storeConverter.toEntity(newStoreDTO);

    store.setId(dto.getId());
    store.setAddress(address);
    store.setGeoLocation(geoLocation);

    store = storeRepository.save(store);

    return storeConverter.toDTO(store);
  }

  @Override
  public void deleteStore(Long storeId) {
    StoreDTO dto = getStore(storeId);
    storeRepository.deleteById(storeId);
  }

  private Store getNearestStore(Double givenLatitude, Double givenLongitude, Page<Store> stores) {
    Function<Store, Double> getDistance =
        store -> {
          Double storeLongitude = store.getGeoLocation().getLongitude();
          Double storeLatitude = store.getGeoLocation().getLatitude();
          return Math.sqrt(
              Math.pow(storeLongitude - givenLongitude, 2)
                  + Math.pow(storeLatitude - givenLatitude, 2));
        };
    return stores
        .get()
        .min(Comparator.comparing(getDistance))
        .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOTFOUND_MESSAGE));
  }

}
