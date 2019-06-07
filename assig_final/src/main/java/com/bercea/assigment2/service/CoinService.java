package com.bercea.assigment2.service;

import java.util.List;

import com.bercea.assigment2.model.Coin;

public interface CoinService {
	List<Coin> retrieveCoin(String desc);
	
	void addCoin(Coin coin);
	
	void deleteCoin(Coin coin);
	
	
}
