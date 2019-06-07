package com.bercea.assigment2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bercea.assigment2.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
	List<Coin> findByDesc(String desc);

	

	void delete(Coin coin);
}
