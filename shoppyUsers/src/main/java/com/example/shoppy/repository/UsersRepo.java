package com.example.shoppy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppy.entity.User;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {
	
	Boolean existsByEmail(String email);

	User findByEmail(String email);
	
	Optional<User> findByUserIdAndIsActiveTrue(Integer id);

}
