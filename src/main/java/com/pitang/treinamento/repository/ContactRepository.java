package com.pitang.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.treinamento.model.ContactModel;

public interface ContactRepository extends JpaRepository<ContactModel, Long>{
	
}
