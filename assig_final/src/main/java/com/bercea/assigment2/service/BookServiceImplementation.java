package com.bercea.assigment2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.BookOrder;
import com.bercea.assigment2.model.Observer;
import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.BookOrderRepository;
import com.bercea.assigment2.repository.BookRepository;

@Service
@Transactional
public class BookServiceImplementation implements BookService {
	@Autowired
	private UserService userService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookOrderRepository bookOrderRepository;
	@Autowired
	private ObserverService observerService;

	public static List<Book> books = new ArrayList<Book>();

	public List<Book> retrieveBooks(String author) {
		List<Book> filteredBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				filteredBooks.add(book);
			}
		}
		return filteredBooks;
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}

	@Override
	public void borrowBook(Integer bookId, String userMail) {
		if (!bookRepository.findById(bookId).isPresent()) {
			return;
		}
		User user = userService.getUserByEmail(userMail);
		if (user == null) {
			return;
		}
		Book book = bookRepository.findById(bookId).get();
		System.out.println(book.isAvailable());
		if (book.isAvailable()) {
			BookOrder bookOrder = new BookOrder();
			bookOrder.setUser(user);
			bookOrder.setBook(book);
			book.setAvailable(false);
			bookOrderRepository.save(bookOrder);
		} else {
			System.out.println("observer");
			Observer observer = new Observer();
			observer.setMail(user.getEmail());
			observerService.addObserver(book, observer);
		}
	}

	@Override
	public void sendBookBack(Integer bookOrderId) {
		BookOrder bookOrder = bookOrderRepository.findById(bookOrderId).get();
		Optional<Book> optionalBook = bookRepository.findById(bookOrder.getBook().getId());
		if (!optionalBook.isPresent()) {
			System.out.println("No book found!");
			return;
		}
		Book book = optionalBook.get();
		observerService.notifyObservers(book);
		book.setAvailable(true);
		bookRepository.save(book);
		bookOrderRepository.delete(bookOrder);
	}

	@Override
	public List<BookOrder> getOrdersForAnUserByEmail(String email) {
		return bookOrderRepository.findBooksByUserEmail(email);
	}

	@Override
	public BookOrder getBookOrderById(Integer id) {
		return bookOrderRepository.findById(id).get();
	}
}
