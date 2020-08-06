package com.yahupc.redessociales.services;

import java.util.List;

import com.yahupc.redessociales.model.SocialMedia;
import com.yahupc.redessociales.model.UserSocialMedia;

public interface SocialMediaService {
	void saveSocialMedia(SocialMedia socialMedia);
	
	void deleteSocialMediaById(Long id);
	
	void updateSocialMedia(SocialMedia socialMedia);
	
	List<SocialMedia> findAllSocialMedias();
	
	SocialMedia findById(Long idSocialMedia);
	
	SocialMedia findByName(String name);
	
	UserSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname);

	UserSocialMedia findSocialMediaByIdUserAndIdSocialMedia(Long idUser, Long idSocialMedia);

}
