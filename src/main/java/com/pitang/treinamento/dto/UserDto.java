package com.pitang.treinamento.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private UserProfileDto userProfileDto;
	private List<UserDto> contacts;
	private List<MessageDto> messages;
	private List<StoryDto> stories;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserProfileDto getUserProfileDto() {
		return userProfileDto;
	}
	public void setUserProfileDto(UserProfileDto userProfileDto) {
		this.userProfileDto = userProfileDto;
	}
	public List<UserDto> getContacts() {
		return contacts;
	}
	public void setContacts(List<UserDto> contacts) {
		this.contacts = contacts;
	}
	public List<MessageDto> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}
	public List<StoryDto> getStories() {
		return stories;
	}
	public void setStories(List<StoryDto> stories) {
		this.stories = stories;
	}
	
}
