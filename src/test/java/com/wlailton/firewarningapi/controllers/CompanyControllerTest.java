package com.wlailton.firewarningapi.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wlailton.firewarningapi.models.Company;
import com.wlailton.firewarningapi.repositories.CompanyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(companyRepository).isNotNull();
	}

	@Test
	public void getCompanies() throws Exception {
		mockMvc.perform(get("/api/company/companies")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void getCompany() throws Exception {
		mockMvc.perform(get("/api/company/23639953000108")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void postCompany() throws Exception {
		Company company = new Company();
		company.setCnpj("57342905738796");
		company.setContact("7978987987");
		company.setFantasyName("Junit");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(company);

		mockMvc.perform(post("/api/company/")
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
					.header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1MDc5MTEzOH0.9k6fIPvZcO-oo2NTNwX5i32NiSP9Lisjxx6NMJbNwLmPwgXnJ0vU32cLMOTTgEw_hurAxW-ufT3vCjY34TqVkQ"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

}
