package com.yahupc.redessociales.dao;

import java.util.List;

import com.yahupc.redessociales.model.SocialMedia;
import com.yahupc.redessociales.model.UserSocialMedia;

public interface SocialMediaDao {
	
	void saveSocialMedia(SocialMedia socialMedia);
	
	void deleteSocialMediaById(Long id);
	
	void updateSocialMedia(SocialMedia socialMedia);
	
	List<SocialMedia> findAllSocialMedias();
	
	SocialMedia findById(Long idSocialMedia);
	
	SocialMedia findByName(String name);
	
	UserSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname);

	UserSocialMedia findSocialMediaByIdUserAndIdSocialMedia(Long idUser, Long idSocialMedia);

}
