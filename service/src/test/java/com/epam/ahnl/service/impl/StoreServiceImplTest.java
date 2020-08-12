package com.epam.ahnl.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.epam.ahnl.converter.AddressConverter;
import com.epam.ahnl.converter.GeoLocationConverter;
import com.epam.ahnl.converter.StoreConverter;
import com.epam.ahnl.dto.AddressDTO;
import com.epam.ahnl.dto.GeoLocationDTO;
import com.epam.ahnl.dto.PageDTO;
import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.Address;
import com.epam.ahnl.entity.CompanyCode;
import com.epam.ahnl.entity.GeoLocation;
import com.epam.ahnl.entity.Store;
import com.epam.ahnl.repository.AddressRepository;
import com.epam.ahnl.repository.GeoLocationRepository;
import com.epam.ahnl.repository.StoreRepository;
import com.epam.ahnl.service.StoreService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/*
import org.junit.jupiter.api.extension.ExtendWith;
*/
/*import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;*//*

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
*/

@ExtendWith(SpringExtension.class)
class StoreServiceImplTest {

  @Mock
  public GeoLocationRepository geoLocationRepository;
  @Mock
  public AddressRepository addressRepository;
  @Mock
  public StoreRepository storeRepository;
  @Mock
  public StoreConverter storeConverter;
  @Mock
  public GeoLocationConverter geoLocationConverter;
  @Mock
  public AddressConverter addressConverter;
  @Mock
  public ModelMapper modelMapper;
  public StoreService storeService;

  public List<Address> addressList;
  public List<AddressDTO> addressDTOS;
  public List<GeoLocation> geoLocations;
  public List<GeoLocationDTO> geoLocationDTOS;
  public List<StoreDTO> storeDTOList;
  public List<Store> stores;

  @BeforeEach
  void setUp() {
    storeService = Mockito.spy(
        new StoreServiceImpl(storeRepository,addressRepository,geoLocationRepository,
            geoLocationConverter, storeConverter, addressConverter));

    geoLocations = initGeoLocations();
    geoLocationDTOS = initGeoLocationDTOS();
    addressList = initAddress();
    addressDTOS = initAddressDTOS();
    storeDTOList = initStoreDTOS();
    stores = initStores();
  }

  @Test
  void getStore() {
    Optional<Store> storeOptional = Optional.of(stores.get(0));
    StoreDTO expected = storeDTOList.get(0);

    doReturn(expected).when(storeConverter).toDTO(storeOptional.get());
    doReturn(storeOptional).when(storeRepository).findById(storeOptional.get().getId());

    StoreDTO actual = storeService.getStore(storeOptional.get().getId());

    assertEquals(expected,actual);
  }

  @Test
  void getAllStores() {
    PageRequest pageRequest = PageRequest.of(0,stores.size());
    Page<Store> page = new PageImpl<Store>(stores, pageRequest,
        Integer.valueOf(stores.size()).longValue());
    PageDTO<StoreDTO> expectedStoreDTOPageDTO = PageDTO.<StoreDTO>builder()
        .results(storeDTOList)
        .totalResults(Integer.valueOf(stores.size()).longValue())
        .url(null)
        .build();

    for (int i = 0; i < storeDTOList.size(); i++) {
      doReturn(storeDTOList.get(i)).when(storeConverter).toDTO(stores.get(i));
    }

    doReturn(page).when(storeRepository).findAll(pageRequest);

    PageDTO<StoreDTO> actual = storeService.getAllStores(stores.size(),1);

    assertEquals(expectedStoreDTOPageDTO,actual);
  }

  @Test
  void getAllStoresByCompanyCode() {
    PageRequest pageRequest = PageRequest.of(0, 2);
    Page<Store> page =
        new PageImpl<Store>(
            Arrays.asList(stores.get(1), stores.get(2)),
            pageRequest,
            2L);
    PageDTO<StoreDTO> expectedStoreDTOPageDTO =
        PageDTO.<StoreDTO>builder()
            .results(Arrays.asList(storeDTOList.get(1), storeDTOList.get(2)))
            .totalResults(2L)
            .url(null)
            .build();

    for (int i = 0; i < storeDTOList.size(); i++) {
      doReturn(storeDTOList.get(i)).when(storeConverter).toDTO(stores.get(i));
    }
    CompanyCode code = CompanyCode.ET;
    doReturn(page).when(storeRepository).findAllByCompanyCode(code, pageRequest);

    PageDTO<StoreDTO> actual = storeService.getAllStoresByCompanyCode(code,2,1);

    assertEquals(expectedStoreDTOPageDTO,actual);
  }

  @Test
  void getNearestStoreByCompanyCode() {}

  @Test
  void addStore() {}

  @Test
  void editStore() {}

  @Test
  void deleteStore() {}

  private List<StoreDTO> initStoreDTOS() {
    StoreDTO expectedStoreDTO1 = StoreDTO.builder()
        .id(1L)
        .name("Store Name1")
        .address(addressDTOS.get(0))
        .geoLocation(geoLocationDTOS.get(0))
        .companyCode(CompanyCode.AH)
        .phone("+123-555-1235422")
        .build();
    StoreDTO expectedStoreDTO2 = StoreDTO.builder()
        .id(2L)
        .name("Store Name2")
        .address(addressDTOS.get(1))
        .geoLocation(geoLocationDTOS.get(1))
        .companyCode(CompanyCode.ET)
        .phone("+123-555-1235422")
        .build();
    StoreDTO expectedStoreDTO3 = StoreDTO.builder()
        .id(3L)
        .name("Store Name3")
        .address(addressDTOS.get(2))
        .geoLocation(geoLocationDTOS.get(2))
        .companyCode(CompanyCode.GA)
        .phone("+123-555-1235422")
        .build();
    return Arrays.asList(expectedStoreDTO1,expectedStoreDTO2,expectedStoreDTO3);
  }

  private List<Store> initStores() {
    Store store1 = Store.builder()
        .id(1L)
        .name("Store Name1")
        .address(addressList.get(0))
        .geoLocation(geoLocations.get(0))
        .companyCode(CompanyCode.AH)
        .phone("+123-555-1235422")
        .build();
    Store store2 = Store.builder()
        .id(2L)
        .name("Store Name2")
        .address(addressList.get(1))
        .geoLocation(geoLocations.get(1))
        .companyCode(CompanyCode.ET)
        .phone("+123-555-1235422")
        .build();
    Store store3 = Store.builder()
        .id(3L)
        .name("Store Name2")
        .address(addressList.get(2))
        .geoLocation(geoLocations.get(2))
        .companyCode(CompanyCode.ET)
        .phone("+123-555-1235422")
        .build();
    return Arrays.asList(store1,store2,store3);
  }

  private List<Address> initAddress() {
    Address address = Address.builder()
        .street("Street Name")
        .city("City Name")
        .countryCode("AE")
        .houseNumber(123)
        .postalCode("AD231")
        .build();
    return Arrays.asList(address,address,address);
  }

  private List<AddressDTO> initAddressDTOS() {
    AddressDTO expectedAddressDTO = AddressDTO.builder()
        .street("Street Name")
        .city("City Name")
        .countryCode("AE")
        .houseNumber(123)
        .postalCode("AD231")
        .build();
    return Arrays.asList(expectedAddressDTO,expectedAddressDTO,expectedAddressDTO);
  }

  private List<GeoLocation> initGeoLocations() {
    GeoLocation geoLocation = GeoLocation.builder()
        .id(1L)
        .latitude(100.2323)
        .longitude(300.1231)
        .build();
    GeoLocation geoLocation2 = GeoLocation.builder()
        .id(2L)
        .latitude(10.2323)
        .longitude(30.1231)
        .build();
    GeoLocation geoLocation3 = GeoLocation.builder()
        .id(3L)
        .latitude(1.2323)
        .longitude(3.1231)
        .build();
    return Arrays.asList(geoLocation,geoLocation2,geoLocation3);
  }

  private List<GeoLocationDTO> initGeoLocationDTOS() {
    GeoLocationDTO expectedGeoLocationDTO = GeoLocationDTO.builder()
        .latitude(100.2323)
        .longitude(300.1231)
        .build();
    GeoLocationDTO expectedGeoLocationDTO2 = GeoLocationDTO.builder()
        .latitude(10.2323)
        .longitude(30.1231)
        .build();
    GeoLocationDTO expectedGeoLocationDTO3 = GeoLocationDTO.builder()
        .latitude(1.2323)
        .longitude(3.1231)
        .build();
    return Arrays.asList(expectedGeoLocationDTO,expectedGeoLocationDTO2,expectedGeoLocationDTO3);
  }
}
