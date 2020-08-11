package com.epam.ahnl.repository;

import com.epam.ahnl.entity.CompanyCode;
import com.epam.ahnl.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

  Page<Store> findAllByCompanyCode(CompanyCode companyCode, Pageable pageRequest);
}
