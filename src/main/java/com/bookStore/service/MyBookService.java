package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;

@Service
public class MyBookService {
	@Autowired
	private MyBookRepository mybookrepository;
	public void saveMyBook( MyBookList book) {
		mybookrepository.save(book);
		
	}
	
	public List<MyBookList> getAllMyBooks(){
		return mybookrepository.findAll();
	}
	
	public void deleteById(int id) {
		mybookrepository.deleteById(id);
	}
	
	
	

}
