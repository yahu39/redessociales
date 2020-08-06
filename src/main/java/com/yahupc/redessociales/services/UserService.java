package com.yahupc.redessociales.services;

import java.util.List;

import com.yahupc.redessociales.model.User;

public interface UserService {
	void saveUser(User user);
	List<User> findAllUsers();
	void deleteUserById(Long idUser);
	void updateUser(User user);
	User findById(Long idUser);
	User findByName(String name);
}
