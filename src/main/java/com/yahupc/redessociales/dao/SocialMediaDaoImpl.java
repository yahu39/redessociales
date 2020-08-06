package com.yahupc.redessociales.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yahupc.redessociales.model.SocialMedia;
import com.yahupc.redessociales.model.UserSocialMedia;

@Repository
@Transactional
public class SocialMediaDaoImpl extends AbstractSession implements SocialMediaDao{

	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		getSession().persist(socialMedia);
	}

	@Override
	public void deleteSocialMediaById(Long id) {
		// TODO Auto-generated method stub
		SocialMedia socialMedia = findById(id);
		if(socialMedia != null) {
			getSession().delete(socialMedia);
		}
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		getSession().update(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedias() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from SocialMedia").list();
	}

	@Override
	public SocialMedia findById(Long idSocialMedia) {
		// TODO Auto-generated method stub
		return (SocialMedia) getSession().get(SocialMedia.class, idSocialMedia);
	}

	@Override
	public SocialMedia findByName(String name) {
		// TODO Auto-generated method stub
		return (SocialMedia) getSession().createQuery("from SocialMedia where name = :name")
				.setParameter("name", name).uniqueResult();
	
	}

	@Override
	public UserSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		// TODO Auto-generated method stub
		List<Object[]> objects = getSession().createQuery(
				"from UserSocialMedia usm join usm.socialMedia sm "
				+ "where sm.idSocialMedia = :idSocialMedia and usm.nickname = :nickname")
				.setParameter("idSocialMedia", idSocialMedia)
				.setParameter("nickname", nickname).list();
		
		if (objects.size() > 0) {
			for (Object[] objects2 : objects) {
				for (Object object : objects2) {
					if (object instanceof UserSocialMedia) {
						return (UserSocialMedia) object;
					}
				}
			}
		}
		
		return null;
	}


	@Override
	public UserSocialMedia findSocialMediaByIdUserAndIdSocialMedia(Long idUser, Long idSocialMedia) {
		// TODO Auto-generated method stub
		List<Object[]> objs = getSession().createQuery(
				"from UserSocialMedia usm join usm.socialMedia sm "
				+ "join usm.user u where sm.idSocialMedia = :id_social_media "
				+ "and u.idUser = :id_user")
				.setParameter("id_social_media", idSocialMedia)
				.setParameter("id_user", idUser).list();
		
		if (objs.size()>0) {
			for (Object[] objects : objs) {
				for (Object object : objects) {
					if (object instanceof UserSocialMedia) {
						return (UserSocialMedia) object;
					}
				}
			}
		}
		return null;
	}

}
