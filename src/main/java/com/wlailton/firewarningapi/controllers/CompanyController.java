package com.wlailton.firewarningapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * Create company.
	 */
	@PostMapping("/companies")
	public Company createCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}
}
