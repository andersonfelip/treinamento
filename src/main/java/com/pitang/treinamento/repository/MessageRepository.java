package com.pitang.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.treinamento.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Long>{

}
