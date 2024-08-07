package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Seller;

public interface SellerDao extends JpaRepository<Seller, Long>{
	

}
