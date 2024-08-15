package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

import com.app.entities.Role;
import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long>{
	List<User> findByRole(Role role);
	Optional<User> findByEmailAndPassword(String email, String pwd);
	Optional<User> findByEmail(String email);
}
