package com.wlailton.firewarningapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wlailton.firewarningapi.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query("SELECT c FROM Company c WHERE c.cnpj = :cnpj")
	public Optional<Company> findByCNPJ(@Param("cnpj") String cnpj);
	
}
