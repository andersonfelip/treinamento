package com.pitang.treinamento.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "message")
public class MessageModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "message")
    private String message;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_owner_id", nullable = false)
    private UserModel userOwner;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_target_id", nullable = false)
    private UserModel userTarget;
    
    @NotNull
    @Column(name = "status_owner")
    private Boolean statusOwner;
    
    @NotNull
    @Column(name = "status_target")
    private Boolean statusTarget;
    
    @NotNull
    @Column(unique = true)
    private Date date;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getStatusOwner() {
		return statusOwner;
	}

	public void setStatusOwner(Boolean statusOwner) {
		this.statusOwner = statusOwner;
	}

	public Boolean getStatusTarget() {
		return statusTarget;
	}

	public void setStatusTarget(Boolean statusTarget) {
		this.statusTarget = statusTarget;
	}
	
}
