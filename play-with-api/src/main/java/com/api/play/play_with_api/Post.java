package com.api.play.play_with_api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	
	protected Post() {
		//default constructor
	}

	public Post(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Userr user;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Userr getUser() {
		return user;
	}

	public void setUser(Userr user) {
		this.user = user;
	}
}
	