package com.pitang.treinamento.service;

import java.util.List;

import com.pitang.treinamento.model.UserModel;


public interface UserService {
	
	public List<UserModel> listUsers();

	public UserModel findUserByUsername(String userName);
	
	public UserModel addUser(UserModel user);
}
