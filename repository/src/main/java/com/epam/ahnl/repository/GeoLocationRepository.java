package com.epam.ahnl.repository;

import com.epam.ahnl.entity.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {}
