package com.wlailton.firewarningapi.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlailton.firewarningapi.enums.Status;
import com.wlailton.firewarningapi.exceptions.CompanyNotFoundException;
import com.wlailton.firewarningapi.models.Company;
import com.wlailton.firewarningapi.models.Incident;
import com.wlailton.firewarningapi.repositories.CompanyRepository;
import com.wlailton.firewarningapi.repositories.IncidentRepository;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private IncidentRepository incidentRepository;

	/**
	 * Post incident.
	 */
	@PostMapping("/{cnpj}")
	public Incident postIncident(@PathVariable String cnpj, @Valid @RequestBody Incident incident) {

		Company company = companyRepository.findByCNPJ(cnpj)
				.orElseThrow(() -> new CompanyNotFoundException(cnpj));
		Set<Incident> incidents = new HashSet<Incident>();
		incidents.add(incident);
		company.setIncidents(incidents);

		incident.setCompany(company);

		return incidentRepository.save(incident);
	}

	/**
	 * Put incident.
	 */
	@PutMapping("/{cnpj}")
	public Incident putIncident(@PathVariable String cnpj, @Valid @RequestBody Incident incident) {

		return companyRepository.findByCNPJ(cnpj).map(company -> {
			Incident incident2 = company.getLatestIncident();
			incident.setId(incident2.getId());
			incident.setDate(incident2.getDate());
			incident.setCompany(incident2.getCompany());
			if (incident.getStatus().equals(Status.RESOLVED)) {
				incident.setDateResolution(new Date());
			} else {
				incident.setDateResolution(null);
			}
			return incidentRepository.save(incident);
		}).orElseThrow(() -> new CompanyNotFoundException(cnpj));

	}
}
