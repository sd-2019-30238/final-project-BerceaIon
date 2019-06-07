package com.bercea.assigment2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
// observable
// contine o lista de observeri care observa catre el
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;

	@Column(name = "author")
	private String author;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "genre")
	private String genre;

	@Column(name = "price")
	private int price;

	@Column
	private boolean available;

	@OneToMany(mappedBy = "book")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Observer> observers = new ArrayList<>();

	@OneToOne(mappedBy = "book")
	@Cascade(CascadeType.SAVE_UPDATE)
	private BookOrder bookOrder;

	public Book() {}

	public BookOrder getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

	public Book(int id, String author, String release, String genre, int price) {
		this.id = id;
		this.author = author;
		releaseDate = release;
		this.genre = genre;
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
