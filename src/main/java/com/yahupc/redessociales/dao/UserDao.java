package com.yahupc.redessociales.dao;

import java.util.List;

import com.yahupc.redessociales.model.User;


public interface UserDao {
	void saveUser(User user);
	List<User> findAllUsers();
	void deleteUserById(Long idUser);
	void updateUser(User user);
	User findById(Long idUser);
	User findByName(String name);
}
