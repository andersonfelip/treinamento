package com.pitang.treinamento.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class ContactModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_owner_id", nullable = false)
    private UserModel userOwner;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_target_id", nullable = false)
    private UserModel userTarget;
	

	public ContactModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserModel userOwner) {
		this.userOwner = userOwner;
	}

	public UserModel getUserTarget() {
		return userTarget;
	}

	public void setUserTarget(UserModel userTarget) {
		this.userTarget = userTarget;
	}
	
}
