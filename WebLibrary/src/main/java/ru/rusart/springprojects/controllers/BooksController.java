package ru.rusart.springprojects.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rusart.springprojects.dao.BookDAO;
import ru.rusart.springprojects.dao.PersonDAO;
import ru.rusart.springprojects.models.Book;
import ru.rusart.springprojects.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
	
	private final PersonDAO personDAO;
	private final BookDAO bookDAO;
	
	@Autowired
	public BooksController(PersonDAO personDAO, BookDAO bookDAO) {
		this.personDAO = personDAO;
		this.bookDAO = bookDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookDAO.index());
		return "books/index";
	}
	
	@PostMapping()
	public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "books/new";
		}
		
		bookDAO.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/new")
	public String showNewBook(@ModelAttribute("book") Book book) {
		return "books/new";
	}
	
	@GetMapping("/{id}")
	public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookDAO.show(id));
		model.addAttribute("people", personDAO.index());
		model.addAttribute("reader", bookDAO.showReader(id));
		
		return "books/show";
	}
	
	@PutMapping("/{id}")
	public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
		bookDAO.assign(id, person.getPersonId());
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}")
	public String freeBook(@PathVariable("id") int id) {
		bookDAO.free(id);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}/edit")
	public String showEdit(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookDAO.show(id));
		return "books/edit";
	}
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookDAO.delete(id);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/edit")
	public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "books/{id}/edit";
		}
		
		bookDAO.update(id, book);
		return "redirect:/books";
	}
}
