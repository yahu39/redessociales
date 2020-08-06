package com.yahupc.redessociales.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yahupc.redessociales.dao.SocialMediaDao;
import com.yahupc.redessociales.model.SocialMedia;
import com.yahupc.redessociales.model.UserSocialMedia;


@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService{
	
	@Autowired 
	private SocialMediaDao _socialMediaDao;
	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.saveSocialMedia(socialMedia);
	}

	@Override
	public void deleteSocialMediaById(Long id) {
		// TODO Auto-generated method stub
		_socialMediaDao.deleteSocialMediaById(id);
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.updateSocialMedia(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedias() {
		// TODO Auto-generated method stub
		return _socialMediaDao.findAllSocialMedias();
	}

	@Override
	public SocialMedia findById(Long idSocialMedia) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findById(idSocialMedia);
	}

	@Override
	public SocialMedia findByName(String name) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findByName(name);
	}

	@Override
	public UserSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findSocialMediaByIdAndName(idSocialMedia, nickname);
	}

	@Override
	public UserSocialMedia findSocialMediaByIdUserAndIdSocialMedia(Long idUser, Long idSocialMedia) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findSocialMediaByIdUserAndIdSocialMedia(idUser, idSocialMedia);
	}

}
