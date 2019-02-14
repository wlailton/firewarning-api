package com.wlailton.firewarningapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlailton.firewarningapi.security.AccountCredentials;

@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class AbstractMvcTest {
	@Autowired
	protected MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
    
	protected String json(Object o) throws IOException {
        return mapper.writeValueAsString(o);
    }
    
    protected ResultActions login(String username, String password) throws Exception {
        final AccountCredentials auth = new AccountCredentials();
        auth.setUsername(username);
        auth.setPassword(password);
        return mockMvc.perform(
                post("/login")
                        .content(json(auth))
                        .contentType(MediaType.APPLICATION_JSON));
    }
    
    protected String extractToken(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getHeader("Authorization").replaceFirst("JWT ", "");
    }
}
