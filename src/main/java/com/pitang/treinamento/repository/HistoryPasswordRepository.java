package com.pitang.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.treinamento.model.HistoryPasswordsModel;

public interface HistoryPasswordRepository extends JpaRepository<HistoryPasswordsModel, Long>{

}
