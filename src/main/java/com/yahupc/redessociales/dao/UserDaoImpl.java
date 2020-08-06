package com.yahupc.redessociales.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yahupc.redessociales.model.User;
import com.yahupc.redessociales.model.UserSocialMedia;


@Repository
@Transactional
public class UserDaoImpl extends AbstractSession implements UserDao{

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		getSession().persist(user);
	}

	
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from User").list();
	}

	
	public void deleteUserById(Long idUser) {
		// TODO Auto-generated method stub
		User user = findById(idUser);
		if(user != null) {
			Iterator<UserSocialMedia> i = user.getUserSocialMedias().iterator();
			while(i.hasNext()) {
				UserSocialMedia userSocialMedia = i.next();
				i.remove();
				getSession().delete(userSocialMedia);
			}
			user.getUserSocialMedias().clear();
			getSession().delete(user);
		}
	}

	
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		getSession().update(user);
	}

	
	public User findById(Long idUser) {
		// TODO Auto-generated method stub
		return getSession().get(User.class, idUser);
	}

	
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return (User) getSession().createQuery(
				"from User where name = :name")
				.setParameter("name", name).uniqueResult();
	}

}
