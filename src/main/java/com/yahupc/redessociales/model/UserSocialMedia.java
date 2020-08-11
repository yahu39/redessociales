package com.yahupc.redessociales.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_social_media")
public class UserSocialMedia implements Serializable{

	@Id
	@Column(name="id_user_social_media")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUserSocialMedia;	
	
	@Column(name="nickname")
	private String nickname;
	
	@ManyToOne(fetch=FetchType.EAGER) // que sea obligatorio
	@JoinColumn(name="id_user")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER) // que sea obligatorio
	@JoinColumn(name="id_social_media")
	private SocialMedia socialMedia;
	

	
	public UserSocialMedia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserSocialMedia(User user, SocialMedia socialMedia, String nickname) {
		super();
		this.user = user;
		this.socialMedia = socialMedia;
		this.nickname = nickname;
	}
	public Long getIdUserSocialMedia() {
		return idUserSocialMedia;
	}
	public void setIdUserSocialMedia(Long idUserSocialMedia) {
		this.idUserSocialMedia = idUserSocialMedia;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SocialMedia getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	

}
