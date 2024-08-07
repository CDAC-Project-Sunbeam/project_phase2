package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Wishlist;

public interface WishlistDao extends JpaRepository<Wishlist, Long>{

}
