package com.epam.ahnl.controller;

import com.epam.ahnl.dto.PageDTO;
import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.CompanyCode;
import com.epam.ahnl.service.StoreService;
import java.util.List;
import javax.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StoreDTO addStore(@RequestBody StoreDTO storeDTO) {
    return storeService.addStore(storeDTO);
  }

  @GetMapping("/{storeId}")
  public StoreDTO getStore(@PathVariable Long storeId) {
    return storeService.getStore(storeId);
  }

  @GetMapping
  public PageDTO<StoreDTO> getAllStores(@RequestParam(required = false, defaultValue = "5") Integer size,
      @RequestParam(required = false, defaultValue = "1") Integer page) {
    return storeService.getAllStores(size, page);
  }

  @GetMapping("/filter/company-code/{code}")
  public PageDTO<StoreDTO> getAllStoresByCompanyCode(
      @RequestParam(required = false, defaultValue = "5") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page, @PathVariable CompanyCode code) {
    PageDTO<StoreDTO> pageDTO = storeService.getAllStoresByCompanyCode(code, size, page);
    //TODO: String url = ServletConte        get url from request and return
    return pageDTO;
  }

  @GetMapping("/nearest")
  public StoreDTO getNearestStoreByCompanyCode(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam CompanyCode code) {
    return storeService.getNearestStoreByCompanyCode(latitude, longitude, code);
  }

  @PutMapping("/{storeId}")
  @ResponseStatus(HttpStatus.OK)
  public StoreDTO editStore(@PathVariable Long storeId, @RequestBody StoreDTO storeDTO) {
    return storeService.editStore(storeId, storeDTO);
  }

  @DeleteMapping("/{storeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStore(@PathVariable Long storeId) {
    storeService.deleteStore(storeId);
  }
}
