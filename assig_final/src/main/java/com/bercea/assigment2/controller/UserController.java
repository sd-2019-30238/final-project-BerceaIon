package com.bercea.assigment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.UserRepository;
import com.bercea.assigment2.service.UserService;

@Controller
@SessionAttributes("email")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ModelAttribute("auth_user")
	public List<User> getDetails(ModelMap model) {
		String email = getLoggedInUserName(model);

		return userRepository.findUserrByEmail(email);
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/bnr", method = RequestMethod.GET)
	public String exchange(@RequestParam String email, ModelMap model) {
		User user = userService.getUserByEmail(email);
		model.put("user", user);
		return "home";
	}

	@GetMapping("/exchangeRon")
	public String showExchangeForm(Model model, Authentication authentication) {
		model.addAttribute("user", userService.getUserByEmail(authentication.getName()));
		return "bnr-exchange";
	}

	@PutMapping("/exchangeRon/{id}")
	public String exchangeRonToEuro(Authentication authentication, @RequestParam("ron") String ron) {
		userService.exchangeRonToEuro(userService.getUserByEmail(authentication.getName()), Double.parseDouble(ron));
		return "redirect:/exchangeRon";
	}
}
