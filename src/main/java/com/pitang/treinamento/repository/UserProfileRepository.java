package com.pitang.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.treinamento.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
}
