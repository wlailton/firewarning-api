package com.wlailton.firewarningapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlailton.firewarningapi.models.Company;
import com.wlailton.firewarningapi.repositories.CompanyRepository;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;

	/**
	 * Get companies.
	 */
	@GetMapping("/companies")
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	/**
	 * Create company.
	 */
	@PostMapping("/companies")
	public Company createCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}
	
	/**
	 * Get company.
	 */
	@GetMapping("/{cnpj}")
	public Company getCompany(@PathVariable String cnpj) {
		return companyRepository.findByCNPJ(cnpj);

	}
}
