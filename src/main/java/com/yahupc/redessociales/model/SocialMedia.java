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
@Table(name="social_media")
public class SocialMedia implements Serializable{
	
	@Id
	@Column(name="id_social_media")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSocialMedia;
	
	@Column(name="name")
	private String name;
	
	@Column(name="icon")
	private String icon;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_social_media")
	@JsonIgnore
	private Set<UserSocialMedia> userSocialMedias;
	

	public SocialMedia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SocialMedia(String name, String icon) {
		super();
		this.name = name;
		this.icon = icon;
	}
	

	public Long getIdSocialMedia() {
		return idSocialMedia;
	}
	public void setIdSocialMedia(Long idSocialMedia) {
		this.idSocialMedia = idSocialMedia;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	
	public Set<UserSocialMedia> getUserSocialMedias() {
		return userSocialMedias;
	}

	public void setUserSocialMedias(Set<UserSocialMedia> userSocialMedias) {
		this.userSocialMedias = userSocialMedias;
	}

}
