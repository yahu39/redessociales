package com.yahupc.redessociales.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yahupc.redessociales.dao.UserDao;
import com.yahupc.redessociales.model.User;

@Service("userService")
@Transactional 
public class UserServiceImpl implements UserService {
	
	@Autowired // llamar al objeto mas generico que encuentre para implementarse
	private UserDao _userDao;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		_userDao.saveUser(user);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return _userDao.findAllUsers();
	}

	@Override
	public void deleteUserById(Long idUser) {
		// TODO Auto-generated method stub
		_userDao.deleteUserById(idUser);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		_userDao.updateUser(user);
	}

	@Override
	public User findById(Long idUser) {
		// TODO Auto-generated method stub
		return _userDao.findById(idUser);
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return _userDao.findByName(name);
	}

}
