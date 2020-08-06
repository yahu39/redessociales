package com.yahupc.redessociales.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	@Id
	@Column(name="id_user")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(name="name")
	private String name;
	
	@Column(name="avatar")
	private String avatar;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL) // que si se elimina un user se eliminen sus socialmedias en cascada
	@JoinColumn(name="id_user")
	@JsonIgnore
	private Set<UserSocialMedia> userSocialMedias;
	
		
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String avatar) {
		super();
		this.name = name;
		this.avatar = avatar;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long id_user) {
		this.idUser = id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Set<UserSocialMedia> getUserSocialMedias() {
		return userSocialMedias;
	}
	public void setUserSocialMedias(Set<UserSocialMedia> userSocialMedias) {
		this.userSocialMedias = userSocialMedias;
	}

}
