package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Address;

public interface AddressDao extends JpaRepository<Address,Long> {

}
