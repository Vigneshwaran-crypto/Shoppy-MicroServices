package com.example.shoppy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppy.entity.User;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {
	
	Boolean existsByEmail(String email);

	User findByEmail(String email);

}
