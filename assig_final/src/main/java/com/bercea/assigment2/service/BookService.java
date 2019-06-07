package com.bercea.assigment2.service;

import java.util.List;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.BookOrder;

public interface BookService {
	List<Book> retrieveBooks(String author);
	
	void addBook(Book book);
	
	void deleteBook(Book book);
	
	void borrowBook(Integer bookId, String userMail);
	
	void sendBookBack(Integer bookOrderId);
	
	List<BookOrder> getOrdersForAnUserByEmail(String email);
	
	BookOrder getBookOrderById(Integer id);
}
