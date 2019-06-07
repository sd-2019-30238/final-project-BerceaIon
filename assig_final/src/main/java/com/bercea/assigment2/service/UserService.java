package com.bercea.assigment2.service;

import com.bercea.assigment2.model.User;

public interface UserService {
	User getUserByEmail(String email);
	
	void saveUser(User user);
	
	boolean isUserAlreadyPresent(User user);
	
	void exchangeRonToEuro(User user, Double ron);
}
