package com.wlailton.firewarningapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wlailton.firewarningapi.enums.UserType;
import com.wlailton.firewarningapi.models.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenFindById_thenReturnUser() {
		User user = new User();
		user.setName("Test");
		user.setEmail("teste@xpto.com");
		user.setType(UserType.POPULACAO);
	
		entityManager.persist(user);
		entityManager.flush();

		User userFound = userRepository.findById(user.getId())
				.orElseThrow(() -> new RuntimeException());

		assertThat(userFound.getName()).isEqualTo(user.getName());
		assertThat(userFound.getEmail()).isEqualTo(userFound.getEmail());
		assertThat(userFound.getType()).isEqualTo(userFound.getType());
		

	}
}
