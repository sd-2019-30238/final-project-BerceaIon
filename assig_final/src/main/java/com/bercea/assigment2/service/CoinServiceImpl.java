package com.bercea.assigment2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bercea.assigment2.model.Coin;
import com.bercea.assigment2.repository.CoinRepository;

public class CoinServiceImpl implements CoinService {
	@Autowired
	private CoinRepository coinRepository;
	
	private static List<Coin> coins = new ArrayList<Coin>();
	
	@Override
	public List<Coin> retrieveCoin(String desc) {
		List<Coin> filteredCoins = new ArrayList<Coin>();
		for(Coin c:coins) {
			if(c.getDesc().equals(desc)) {
				filteredCoins.add(c);
			}
		return filteredCoins;
		}
		return null;
	}



	@Override
	public void deleteCoin(Coin coin) {
		coinRepository.delete(coin);
		
	}



	@Override
	public void addCoin(Coin coin) {
		// TODO Auto-generated method stub
		
	}
	

}
