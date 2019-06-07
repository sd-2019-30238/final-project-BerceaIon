package com.bercea.assigment2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bercea.assigment2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByEmail(String email);
	List<User> findUserrByEmail(String email);
	
}
