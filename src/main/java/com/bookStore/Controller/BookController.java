package com.bookStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookservice;
	@Autowired
	private MyBookService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	private String bookRegister() {
		return "bookRegister";
		
	}
	
	@GetMapping("/available_book")
	private ModelAndView getAllBook() {
		List<Book>list=bookservice.getAllBook();
	return new ModelAndView("bookList","book",list);
		
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b){
		bookservice.save(b);
		return "redirect:/available_book";
		
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	@GetMapping("/logo")
	public String Logo() {
		return "home";
	}
	
	@RequestMapping("/myList/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=bookservice.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBook(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id")int id ,Model model) {
		Book b=bookservice.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookservice.deleteById(id);
		return "redirect:/available_book";
	}

}
