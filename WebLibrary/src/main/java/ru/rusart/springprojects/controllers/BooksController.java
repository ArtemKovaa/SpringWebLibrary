package ru.rusart.springprojects.controllers;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;

import ru.rusart.springprojects.models.Book;
import ru.rusart.springprojects.models.Person;
import ru.rusart.springprojects.services.BooksService;
import ru.rusart.springprojects.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BooksController {
	
	private final PeopleService peopleService;
	private final BooksService booksService;
	
	@Autowired
	public BooksController(PeopleService peopleService, BooksService booksService) {
		this.peopleService = peopleService;
		this.booksService = booksService;
	}
	
	@GetMapping()
	public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("books_per_page") Optional<Integer> booksPerPage,
			@RequestParam("sort_by_year") Optional<Boolean> sortByYear) {
		model.addAttribute("books", booksService.findAll(page, booksPerPage, sortByYear));
		return "books/index";
	}
	
	@PostMapping()
	public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "books/new";
		}
		
		booksService.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/new")
	public String showNewBook(@ModelAttribute("book") Book book) {
		return "books/new";
	}
	
	@GetMapping("/{id}")
	public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", booksService.findById(id));
		model.addAttribute("people", peopleService.findAll());
		model.addAttribute("reader", booksService.findReader(id));
		
		return "books/show";
	}
	
	@PutMapping("/{id}")
	public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
		booksService.assign(id, person.getPersonId());
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}")
	public String freeBook(@PathVariable("id") int id) {
		booksService.free(id);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}/edit")
	public String showEdit(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", booksService.findById(id));
		return "books/edit";
	}
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		booksService.delete(id);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/edit")
	public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "books/{id}/edit";
		}
		
		booksService.update(id, book);
		return "redirect:/books";
	}
	
	@GetMapping("/search")
	public String search(Model model, @RequestParam("searchTerm") Optional<String> startingWith) {
		System.out.println(1);
		model.addAttribute("books", booksService.findByTitleStartingWith(startingWith));
		
		return "books/search";
	}
}
