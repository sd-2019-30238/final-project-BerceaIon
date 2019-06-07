package com.bercea.assigment2.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.Coin;
import com.bercea.assigment2.model.Observer;
import com.bercea.assigment2.repository.ObserverRepository;
import com.bercea.assigment2.repository.UserRepository;

@Service
@Transactional
public class ObserverServiceImplementation implements ObserverService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObserverRepository observerRepository;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void notifyObservers(Coin coin) {
		for (Observer observer : coin.getObservers()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(observer.getMail());
			message.setSubject("Coin available");
			message.setText("Coin " + coin.getDesc() + " is avaiable!");
			mailSender.send(message);
		}
		for (Observer observer : coin.getObservers()) {
			observerRepository.delete(observer);
		}
	}

	@Override
	public void addObserver(Book book, Observer observer) {
		book.getObservers().add(observer);
		observer.setBook(book);
		observerRepository.save(observer);
	}
	
	@Override
	public void addObserver(Coin coin, Observer observer) {
		coin.getObservers().add(observer);
		observer.setCoin(coin);
		observerRepository.save(observer);
	}

	@Override
	public void notifyObservers(Book book) {
		// TODO Auto-generated method stub
		
	}

}
