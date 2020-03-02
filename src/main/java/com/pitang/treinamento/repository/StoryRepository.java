package com.pitang.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.treinamento.model.StoryModel;

public interface StoryRepository extends JpaRepository<StoryModel, Long>{

}
