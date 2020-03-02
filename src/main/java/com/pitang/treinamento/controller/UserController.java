package com.pitang.treinamento.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pitang.treinamento.dto.UserDto;
import com.pitang.treinamento.dto.UserProfileDto;
import com.pitang.treinamento.exceptions.ExceptionBadRequest;
import com.pitang.treinamento.mapper.ModelMapperComponent;
import com.pitang.treinamento.model.UserModel;
import com.pitang.treinamento.service.UserService;

@RequestMapping(value = "/user")
@RestController
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserDto>> listUsers(){
		List<UserModel> users = userService.listUsers();
		
		if(users.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<UserDto> usersDto = ModelMapperComponent.modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
		
		ModelMapperComponent.modelMapper.validate();
		
		return new ResponseEntity<>(usersDto,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		
		if(userDto == null) {
			throw new ExceptionBadRequest("Não é possível salvar um usuario nulo!");
		}
		
		UserModel userModel = ModelMapperComponent.modelMapper.map(userDto, new TypeToken<UserModel>() {}.getType());
		ModelMapperComponent.modelMapper.validate();
		
		userService.addUser(userModel);
		
		userDto = ModelMapperComponent.modelMapper.map(userModel, new TypeToken<UserDto>() {}.getType());
		ModelMapperComponent.modelMapper.validate();
		
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
		
		if(id == null || userDto == null) {
			throw new ExceptionBadRequest("Não é possível alterar um usuario nulo ou sem id!");
		}
		
		UserModel userModel = ModelMapperComponent.modelMapper.map(userDto, new TypeToken<UserModel>() {}.getType());
		ModelMapperComponent.modelMapper.validate();
		
		userService.updateUser(userModel);
		
		userDto = ModelMapperComponent.modelMapper.map(userModel, new TypeToken<UserDto>() {}.getType());
		ModelMapperComponent.modelMapper.validate();
		
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<UserDto> removeUser(@PathVariable("id") Long id){
		
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}/profileimage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserModel> addProfileImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file)  {
		
		UserDto userDto = new UserDto();
		userDto.setId(id);
		userDto.setUserProfileDto(new UserProfileDto());
		try {
			userDto.getUserProfileDto().setImage(file.getBytes());
		} catch (IOException e) {
			throw new ExceptionBadRequest("Não foi possível recuperar os dados da imagem!");
		}
		
		UserModel userModel = ModelMapperComponent.modelMapper.map(userDto, new TypeToken<UserModel>() {}.getType());
		ModelMapperComponent.modelMapper.validate();
		
		userService.addProfile(userModel);
		
		return new ResponseEntity<>(userModel,HttpStatus.OK);
	}
	
}