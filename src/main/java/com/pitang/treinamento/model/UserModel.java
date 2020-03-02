package com.pitang.treinamento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "username")
    private String username;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 128)
    private String password;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    private ProfileImageModel profileImageModel;
    
    @OneToMany
    private List<UserModel> contacts;
    
    @OneToMany
    private List<MessageModel> messages;
    
    @OneToMany
    private List<StoryModel> stories;
    
    public UserModel() {

    }

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

	public ProfileImageModel getProfileImageModel() {
		return profileImageModel;
	}

	public void setProfileImageModel(ProfileImageModel profileImageModel) {
		this.profileImageModel = profileImageModel;
	}

	public List<UserModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<UserModel> contacts) {
		this.contacts = contacts;
	}

	public List<MessageModel> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageModel> messages) {
		this.messages = messages;
	}

	public List<StoryModel> getStories() {
		return stories;
	}

	public void setStories(List<StoryModel> stories) {
		this.stories = stories;
	}
}