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

import com.wlailton.firewarningapi.AbstractMvcTest;
import com.wlailton.firewarningapi.enums.UserType;
import com.wlailton.firewarningapi.models.User;
import com.wlailton.firewarningapi.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractMvcTest {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contexLoads() throws Exception {
		assertThat(userRepository).isNotNull();
	}

	@Test
	public void getUsers() throws Exception {
		final String token = extractToken(login("admin", "password").andReturn());

		mockMvc.perform(get("/api/user/users").header("Authorization", token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void postUser() throws Exception {
		final String token = extractToken(login("admin", "password").andReturn());

		User user = new User();
		user.setName("Test");
		user.setEmail("teste@xpto.com");
		user.setType(UserType.POPULACAO);
		String requestJson = castObjectToJson(user);

		mockMvc.perform(post("/api/user/").header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(requestJson)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

}
