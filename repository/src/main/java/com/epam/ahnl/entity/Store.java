package com.epam.ahnl.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

  @Id
  @SequenceGenerator(name = "storeSequence", sequenceName = "store_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storeSequence")
  private Long id;

  private String name;

  private String phone;

  @OneToOne(orphanRemoval = true)
  private Address address;

  @OneToOne(orphanRemoval = true)
  private GeoLocation geoLocation;

  @Enumerated(EnumType.STRING)
  private CompanyCode companyCode;

}
