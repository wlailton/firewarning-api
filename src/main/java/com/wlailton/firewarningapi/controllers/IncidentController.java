package com.wlailton.firewarningapi.controllers;

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

		Company company = companyRepository.findByCNPJ(cnpj);
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
		Incident incidentOld = companyRepository.findByCNPJ(cnpj).getLatestIncident();

		if (incidentOld != null) {
			incident.setId(incidentOld.getId());
			incident.setCompany(incidentOld.getCompany());
			return incidentRepository.save(incident);
		}
		return null;
	}
}
