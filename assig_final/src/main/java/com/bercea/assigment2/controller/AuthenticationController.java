package com.bercea.assigment2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.BookRepository;
import com.bercea.assigment2.service.BookServiceImplementation;
import com.bercea.assigment2.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;

	@Autowired
	BookServiceImplementation bookService;

	@Autowired
	BookRepository repository;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	public ModelAndView home() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("home"); // resources/template/home.html
//		return modelAndView;
//	}

//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public ModelAndView adminHome() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("admin"); // resources/template/admin.html
//		return modelAndView;
//	}

//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public String showTodos(ModelMap model) {
//		//String name = getLoggedInUserName(model);
//		//model.put("books", repository.findAll());
//		//model.put("books", service.retrieveTodos(name));
//		return "admin";
//	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ModelAttribute("books")
	public List<Book> getBser() {
		System.out.println("da");
		return (List<Book>) repository.findAll();
	}

//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	@ModelAttribute("books")
//	public List<Book> getBooks() {
//		return (List<Book>) repository.findAll();
//	}

//	@GetMapping("/home")
//	public ModelAndView getBook() {
//		ModelAndView modelAndView = new ModelAndView("home");
//		modelAndView.addObject("books", (List<Book>) repository.findAll());
//		return modelAndView;
//	}

	@RequestMapping(value = "/admin-filter-author", method = RequestMethod.GET)
	@ModelAttribute("books")
	public List<Book> filterByAuthor() {
		// return (List<Book>) bookService.retrieveBooks("Dan");
		return (List<Book>) repository.findByAuthor("Bercea");
		// return (List<Book>) repository.findAll();
	}

	@RequestMapping(value = "/user-filter-author", method = RequestMethod.GET)
	@ModelAttribute("books")
	public List<Book> userFilterByAuthor() {
		// return (List<Book>) bookService.retrieveBooks("Dan");
		return (List<Book>) repository.findByAuthor("Bercea");
		// return (List<Book>) repository.findAll();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please corect the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		// we will save the user is no errors
		else if (userService.isUserAlreadyPresent(user)) {
			modelAndView.addObject("successMessage", "User already exists!");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Registered successfull!");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.GET)
	public ModelAndView addBook() {
		ModelAndView modelAndView = new ModelAndView();
		Book book = new Book();
		modelAndView.addObject("book", book);
		modelAndView.setViewName("add-book"); // resources/template/add-book.html
		return modelAndView;
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public ModelAndView addBook(@Valid Book book, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please corect the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		// we will save the user is no errors

		else {
			bookService.addBook(book);
			modelAndView.addObject("successMessage", "Registered successfull!");
		}
		modelAndView.addObject("book", new Book());
		modelAndView.setViewName("add-book");
		return modelAndView;
	}

	@GetMapping("/delete-book/{id}")
	public String testDeleteTodo(@PathVariable("id") int id) {
		System.out.println(id);
		System.out.println("test");
		repository.deleteById(id);
		return "redirect:/admin";
	}

//	@GetMapping("/test")
//	public ModelAndView tet() {
//		System.out.println("merge");
//		return new ModelAndView("welcome");
//	}

	@PostMapping("/books/{id}")
	public ModelAndView buyBook(@PathVariable("id") Integer bookId, Authentication authentication) {
		System.out.println(bookId);
		System.out.println(authentication.getName());
		bookService.borrowBook(bookId, authentication.getName());
		return new ModelAndView("redirect:/home");
	}
	

}
