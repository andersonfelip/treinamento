package com.pitang.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.treinamento.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	UserModel findByUsername(String userName);
	UserModel findByEmail(String email);
	
}
