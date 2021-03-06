package com.wlailton.firewarningapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wlailton.firewarningapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}