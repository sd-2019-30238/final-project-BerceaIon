package com.bercea.assigment2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bercea.assigment2.model.BookOrder;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer>{
	List<BookOrder> findBooksByUserEmail(String email);
}
