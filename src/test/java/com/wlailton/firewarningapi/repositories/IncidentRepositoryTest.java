package com.wlailton.firewarningapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wlailton.firewarningapi.enums.DangerLevel;
import com.wlailton.firewarningapi.enums.Status;
import com.wlailton.firewarningapi.exceptions.IncidentNotFoundException;
import com.wlailton.firewarningapi.models.Company;
import com.wlailton.firewarningapi.models.Incident;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IncidentRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IncidentRepository incidentRepository;

	@Test
	public void whenFindById_thenReturnIncident() {
		Company company = new Company();
		company.setCnpj("21321323231");
		company.setFantasyName("Test");
		company.setContact("53425324");

		Incident incident = new Incident();
		incident.setStatus(Status.OPEN);
		incident.setComment("Junit Test");
		incident.setDangerLevel(DangerLevel.OK);
		incident.setCompany(company);

		entityManager.persist(company);
		entityManager.persist(incident);
		entityManager.flush();

		Incident incidentFound = incidentRepository.findById(incident.getId())
				.orElseThrow(() -> new IncidentNotFoundException(company.getCnpj()));

		assertThat(incidentFound.getStatus()).isEqualTo(incident.getStatus());
		assertThat(incidentFound.getComment()).isEqualTo(incident.getComment());
		assertThat(incidentFound.getDangerLevel()).isEqualTo(incident.getDangerLevel());
		assertThat(incidentFound.getCompany()).isEqualTo(incident.getCompany());

	}

}
