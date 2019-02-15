package com.wlailton.firewarningapi.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wlailton.firewarningapi.AbstractMvcTest;
import com.wlailton.firewarningapi.enums.DangerLevel;
import com.wlailton.firewarningapi.enums.Status;
import com.wlailton.firewarningapi.models.Company;
import com.wlailton.firewarningapi.models.Incident;
import com.wlailton.firewarningapi.repositories.IncidentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IncidentControllerTest extends AbstractMvcTest {

	@Autowired
	private IncidentRepository incidentRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contexLoads() throws Exception {
		assertThat(incidentRepository).isNotNull();
	}

	@Test
	public void postIncidentAndUpdate() throws Exception {
		final String token = extractToken(login("admin", "password").andReturn());

		Company company = new Company();
		company.setCnpj("689697697696");
		company.setContact("7978987987");
		company.setFantasyName("Junit");

		Incident incident = new Incident();
		incident.setStatus(Status.OPEN);
		incident.setComment("Junit Test");
		incident.setDangerLevel(DangerLevel.DANGER);
		incident.setCompany(company);

		String companyJson = castObjectToJson(company);
		String incidentJson = castObjectToJson(incident);

		mockMvc.perform(post("/api/company/").contentType(MediaType.APPLICATION_JSON_UTF8).content(companyJson)
				.header("Authorization", token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		mockMvc.perform(post("/api/incident/" + company.getCnpj()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(incidentJson).header("Authorization", token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		incident.setStatus(Status.RESOLVED);
		incident.setDangerLevel(DangerLevel.OK);
		
		mockMvc.perform(put("/api/incident/" + company.getCnpj()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(incidentJson).header("Authorization", token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	public void updateIncidentNotExist() throws Exception {
		final String token = extractToken(login("admin", "password").andReturn());
		
		Company company = new Company();
		company.setCnpj("8728945792");
		company.setContact("7978987987");
		company.setFantasyName("Junit");
		
		Incident incident = new Incident();
		incident.setStatus(Status.RESOLVED);
		incident.setDangerLevel(DangerLevel.OK);
		
		String companyJson = castObjectToJson(company);
		String incidentJson = castObjectToJson(incident);
		
		mockMvc.perform(post("/api/company/").contentType(MediaType.APPLICATION_JSON_UTF8).content(companyJson)
				.header("Authorization", token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		mockMvc.perform(put("/api/incident/" + company.getCnpj()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(incidentJson).header("Authorization", token)).andDo(print()).andExpect(status().is(404));
	
	}

}
