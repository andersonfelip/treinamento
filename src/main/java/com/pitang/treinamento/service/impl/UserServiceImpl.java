package com.pitang.treinamento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pitang.treinamento.exceptions.ExceptionBadRequest;
import com.pitang.treinamento.exceptions.ExceptionConflict;
import com.pitang.treinamento.model.UserModel;
import com.pitang.treinamento.repository.UserProfileRepository;
import com.pitang.treinamento.repository.UserRepository;
import com.pitang.treinamento.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	public List<UserModel> listUsers() {
		if(userRepository.findAll().size() == 0) {
			return null;
		}
		return userRepository.findAll();
	}
	
	@Override
	public UserModel findUserByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public UserModel addUser(UserModel user) {
		checkMandatoryFields(user);
		validateUser(user);
		checkRelations(user);
		return userRepository.save(user);
	}
	
	private void checkMandatoryFields(UserModel user) {
		if(user == null) {
			throw new ExceptionBadRequest("Usuário não pode ser nulo!");
		}
		if(StringUtils.isEmpty(user.getEmail())) {
			throw new ExceptionBadRequest("Necessário informar o Email do usuário.");
		}
		if(StringUtils.isEmpty(user.getFirstName())) {
			throw new ExceptionBadRequest("Necessário informar o Primeiro Nome do usuário.");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			throw new ExceptionBadRequest("Necessário informar a Senha do usuário.");
		}
		if(StringUtils.isEmpty(user.getUserName())) {
			throw new ExceptionBadRequest("Necessário informar o Nome do usuário.");
		}
	}
	
	private void validateUser(UserModel user) {
		if(!StringUtils.isEmpty(user.getUserName()) && userRepository.findByUserName(user.getUserName()) != null) {
			throw new ExceptionConflict("Nome de usuário informado já existe!");
		}
		if(!StringUtils.isEmpty(user.getEmail()) && userRepository.findByEmail(user.getEmail()) != null) {
			throw new ExceptionConflict("Email informado já existe!");
		}
	}
	
	private void checkRelations(UserModel user) {
		if(user.getUserProfile() != null && user.getUserProfile().getId() != null &&
				userProfileRepository.findById(user.getUserProfile().getId()) == null) {
			throw new ExceptionBadRequest("Perfil do usuário não encontrado.");
		}else if(user.getUserProfile() != null && user.getUserProfile().getId() == null) {
			user.setUserProfile(null);
		}
	}

}
