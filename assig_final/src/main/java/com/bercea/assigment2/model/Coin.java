package com.bercea.assigment2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="coin")
public class Coin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coin_id")
	private int id;
	
	@Column(name = "description")
	private String desc;
	
	@Column(name="value")
	private float value;
	
	@Column(name="amount")
	private float amount;
	
	@OneToMany(mappedBy = "book")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Observer> observers = new ArrayList<>();
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public Coin() {}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Coin [id=" + id + ", value=" + value + ", amount=" + amount + "]";
	}
	
	
}
