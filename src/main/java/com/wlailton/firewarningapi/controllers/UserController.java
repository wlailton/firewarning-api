package com.wlailton.firewarningapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlailton.firewarningapi.models.User;
import com.wlailton.firewarningapi.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  /**
   * Get users.
   */
  @GetMapping("/users")
  public List<User> getAllUsers() {
	  return userRepository.findAll();
  }
  
  /**
   * Create user.
   */
  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    return userRepository.save(user);
  }
    
}