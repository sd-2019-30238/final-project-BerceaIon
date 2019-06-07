package com.bercea.assigment2.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bercea.assigment2.model.Role;
import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.RoleRepository;
import com.bercea.assigment2.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email).get();
	}

	@Override
	public void exchangeRonToEuro(User user, Double ron) {
		User userToChange = getUserByEmail(user.getEmail());
		userToChange.setRon(Double.parseDouble(userToChange.getRon()) - ron + "");
		userToChange.setEur(String.format("%.2f", Double.parseDouble(userToChange.getEur().replaceAll(",", ".")) + ron * 1 / 4.7).replace(",", "."));
		userRepository.save(userToChange);
	}

}
