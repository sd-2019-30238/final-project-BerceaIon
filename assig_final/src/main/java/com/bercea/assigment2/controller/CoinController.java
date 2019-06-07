package com.bercea.assigment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bercea.assigment2.model.Coin;
import com.bercea.assigment2.repository.CoinRepository;

@Controller
public class CoinController {
//	@Autowired
//	CoinService coinService;
//	
//	@Autowired
//	CoinServiceImpl coinServiceImpl;
	
	@Autowired
	CoinRepository coinRepository;
	
	@RequestMapping(value="/coins", method = RequestMethod.GET)
	@ModelAttribute("coin")
	public List<Coin> getCoins(){
		return coinRepository.findAll();
	}
	

}
