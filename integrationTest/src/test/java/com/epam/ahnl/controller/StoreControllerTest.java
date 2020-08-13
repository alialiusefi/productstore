package com.epam.ahnl.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.ahnl.dto.AddressDTO;
import com.epam.ahnl.dto.GeoLocationDTO;
import com.epam.ahnl.dto.StoreDTO;
import com.epam.ahnl.entity.CompanyCode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
@Sql(scripts = {"classpath:/sql/0_init_tables.sql", "classpath:/sql/1_fill_tables.sql"})
@TestMethodOrder(OrderAnnotation.class)
public class StoreControllerTest {

  private static final String STORE_RESOURCE_PATH = "/api/v1/stores";

  @Autowired
  public MockMvc mockMvc;

  @LocalServerPort
  private int port;

  @Test
  @Order(1)
  public void getStore() {
    AddressDTO address =
        AddressDTO.builder()
            .street("Street HERE")
            .houseNumber(1)
            .postalCode("D123A")
            .city("City here")
            .countryCode("US")
            .build();
    GeoLocationDTO geoLocationDTO =
        GeoLocationDTO.builder()
            .latitude(1.23123)
            .longitude(2.21312)
            .build();
    StoreDTO expected =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store Name")
            .id(1L)
            .companyCode(CompanyCode.AH)
            .build();
    StoreDTO actual =
        RestAssured.given()
            .port(port)
            .when()
            .get(STORE_RESOURCE_PATH + "/1")
            .then()
            .statusCode(200)
            .extract()
            .as(StoreDTO.class);

    assertEquals(actual, expected);
  }

  @Test
  @Order(2)
  void getAllStores() {
    AddressDTO address =
        AddressDTO.builder()
            .street("Street HERE")
            .houseNumber(1)
            .postalCode("D123A")
            .city("City here")
            .countryCode("US")
            .build();
    GeoLocationDTO geoLocationDTO = GeoLocationDTO.builder()
        .latitude(1.23123)
        .longitude(2.21312)
        .build();
    StoreDTO dto =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store Name")
            .id(1L)
            .companyCode(CompanyCode.AH)
            .build();
    GeoLocationDTO geoLocationDTO2 = GeoLocationDTO.builder()
        .latitude(111.23123)
        .longitude(211.21312)
        .build();
    StoreDTO dto2 =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO2)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store Name2")
            .id(2L)
            .companyCode(CompanyCode.ET)
            .build();
    JsonPath results = RestAssured.given()
        .port(port)
        .when()
        .get(STORE_RESOURCE_PATH)
        .then()
        .statusCode(200)
        .body("url", equalTo(STORE_RESOURCE_PATH))
        .body("totalResults", equalTo(2))
    .extract().body().jsonPath();

    List<StoreDTO> actual = results.getList("results",StoreDTO.class);

    assertEquals(Arrays.asList(dto,dto2), actual);
  }

  @Test
  @Order(3)
  void getAllStoresByCompanyCode() {
    AddressDTO address =
        AddressDTO.builder()
            .street("Street HERE")
            .houseNumber(1)
            .postalCode("D123A")
            .city("City here")
            .countryCode("US")
            .build();
    GeoLocationDTO geoLocationDTO = GeoLocationDTO.builder()
        .latitude(1.23123)
        .longitude(2.21312)
        .build();
    StoreDTO dto =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store Name")
            .id(1L)
            .companyCode(CompanyCode.AH)
            .build();
    String url = STORE_RESOURCE_PATH + "/filter/company-code/" + "AH";
    JsonPath results = RestAssured.given()
        .port(port)
        .when()
        .get(url)
        .then()
        .statusCode(200)
        .body("url", equalTo(url))
        .body("totalResults", equalTo(1))
        .extract().body().jsonPath();

    List<StoreDTO> actual = results.getList("results",StoreDTO.class);

    assertEquals(Collections.singletonList(dto), actual);
  }

  @Test
  @Order(4)
  void getNearestStoreByCompanyCode() {
    AddressDTO address =
        AddressDTO.builder()
            .street("Street HERE")
            .houseNumber(1)
            .postalCode("D123A")
            .city("City here")
            .countryCode("US")
            .build();
    GeoLocationDTO geoLocationDTO = GeoLocationDTO.builder()
        .latitude(1.23123)
        .longitude(2.21312)
        .build();
    StoreDTO expected =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store Name")
            .id(1L)
            .companyCode(CompanyCode.AH)
            .build();
    String url = STORE_RESOURCE_PATH + "/nearest";
    StoreDTO actual = RestAssured.given()
        .port(port)
        .when()
        .queryParam("code","AH")
        .queryParam("longitude","0.0")
        .queryParam("latitude","0.0")
        .get(url)
        .then()
        .statusCode(200)
        .extract().as(StoreDTO.class);

    assertEquals(expected, actual);
  }

  @Test
  @Order(5)
  void addStore() {
    String json =
        "{\"name\":\"Store Name\",\"phone\":\"+1-666-6578943\",\"address\":{\"street\":\"Street HERE\",\"houseNumber\":1,\"postalCode\":\"D123A\",\"city\":\"City here\",\"countryCode\":\"US\"}"
            + ",\"geoLocation\":{\"latitude\":1.23123,\"longitude\":2.21312},\"companyCode\":\"AH\"}";
    AddressDTO address =
        AddressDTO.builder()
            .street("Street HERE")
            .houseNumber(1)
            .postalCode("D123A")
            .city("City here")
            .countryCode("US")
            .build();
    GeoLocationDTO geoLocationDTO = GeoLocationDTO.builder()
        .latitude(1.23123)
        .longitude(2.21312)
        .build();
    StoreDTO expected =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store Name")
            .id(1L)
            .companyCode(CompanyCode.AH)
            .build();
    StoreDTO createdDTO = RestAssured.given()
        .port(port)
        .body(json)
        .contentType("application/json")
        .when()
        .post(STORE_RESOURCE_PATH)
        .then()
        .statusCode(201)
        .extract().as(StoreDTO.class);
    expected.setId(createdDTO.getId());
    StoreDTO actualDTOFromDB = RestAssured.given()
        .port(port)
        .when()
        .get(STORE_RESOURCE_PATH + "/" + createdDTO.getId())
        .then()
        .statusCode(200)
        .extract().as(StoreDTO.class);
    assertEquals(expected, actualDTOFromDB);
  }

  @Test
  @Order(6)
  void editStore() {
    String json = "{\"name\":\"Store NameEDITTTTTT\",\"phone\":\"+1-666-6578943\",\"address\":{\"street\":\"Street HERE\",\"houseNumber\":1,\"postalCode\":\"D123A\",\"city\":\"City here\",\"countryCode\":\"US\"}"
        + ",\"geoLocation\":{\"latitude\":1.23123,\"longitude\":2.21312},\"companyCode\":\"AH\"}";
    AddressDTO address =
        AddressDTO.builder()
            .street("Street HERE")
            .houseNumber(1)
            .postalCode("D123A")
            .city("City here")
            .countryCode("US")
            .build();
    GeoLocationDTO geoLocationDTO = GeoLocationDTO.builder()
        .latitude(1.23123)
        .longitude(2.21312)
        .build();
    Long storeID = 1L;
    StoreDTO expected =
        StoreDTO.builder()
            .geoLocation(geoLocationDTO)
            .address(address)
            .phone("+1-666-6578943")
            .name("Store NameEDITTTTTT")
            .id(storeID)
            .companyCode(CompanyCode.AH)
            .build();
    StoreDTO actual = RestAssured.given()
        .port(port)
        .body(json)
        .contentType("application/json")
        .when()
        .put(STORE_RESOURCE_PATH + "/" + storeID)
        .then()
        .statusCode(200)
        .extract().as(StoreDTO.class);

    assertEquals(expected, actual);
  }

  @Test
  @Order(7)
  void deleteStore() {
    RestAssured.given()
        .port(port)
        .when()
        .delete(STORE_RESOURCE_PATH + "/1")
        .then()
        .statusCode(HttpStatus.NO_CONTENT.value());

    RestAssured.given()
            .port(port)
            .when()
            .get(STORE_RESOURCE_PATH + "/1")
            .then()
            .statusCode(404);

  }
}
