package com.wlailton.firewarningapi.repositories;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wlailton.firewarningapi.exceptions.CompanyNotFoundException;
import com.wlailton.firewarningapi.models.Company;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CompanyRepository companyRepository;

	@Test
	public void whenFindByCnpj_thenReturnCompany() {
		Company company = new Company();
		company.setCnpj("21321323231");
		company.setFantasyName("Test");
		company.setContact("53425324");

		entityManager.persist(company);
		entityManager.flush();

		Company companyFound = companyRepository.findByCNPJ(company.getCnpj())
				.orElseThrow(() -> new CompanyNotFoundException(company.getCnpj()));

		assertThat(companyFound.getCnpj()).isEqualTo(company.getCnpj());
		assertThat(companyFound.getFantasyName()).isEqualTo(company.getFantasyName());
		assertThat(companyFound.getContact()).isEqualTo(company.getContact());

	}
}
