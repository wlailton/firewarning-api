package com.wlailton.firewarningapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlailton.firewarningapi.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
