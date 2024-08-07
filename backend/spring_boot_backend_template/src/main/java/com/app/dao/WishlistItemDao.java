package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.WishlistItem;

public interface WishlistItemDao extends JpaRepository<WishlistItem, Long>{

}
