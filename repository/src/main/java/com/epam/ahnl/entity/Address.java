package com.epam.ahnl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Address {

  @Id
  @SequenceGenerator(name = "addressSequence", sequenceName = "address_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
  private Long id;

  private String street;

  private Integer houseNumber;

  private String postalCode;

  private String city;

  private String countryCode;

}
