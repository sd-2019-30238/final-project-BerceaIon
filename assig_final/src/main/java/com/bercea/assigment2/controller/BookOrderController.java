package com.bercea.assigment2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bercea.assigment2.service.BookService;

@Controller
@RequestMapping("/orders")
public class BookOrderController {
	@Autowired
	private BookService bookService;

	@GetMapping
	public ModelAndView getAllOrdersForAnUser(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("bookOrders");
		modelAndView.addObject("bookOrders", bookService.getOrdersForAnUserByEmail(authentication.getName()));
		return modelAndView;
	}
	
	@DeleteMapping("/sendBookBack/{id}")
	public ModelAndView sendBookBack(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/orders");
		bookService.sendBookBack(id);
		return modelAndView;
	}
}
