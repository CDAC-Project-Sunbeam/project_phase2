package com.app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Customer;
import com.app.entities.Role;
import com.app.entities.User;

public interface AdminDao extends JpaRepository<User, Long> {
	 @Query("select u from User u where u.role = ?1")
	  List<User> findByRole(Role role);
}
