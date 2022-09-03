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
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rusart.springprojects.models.Person;
import ru.rusart.springprojects.services.BooksService;
import ru.rusart.springprojects.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {

	private final PeopleService peopleService;
	private final BooksService booksService;
	
	@Autowired
	public PeopleController(PeopleService peopleService, BooksService booksService) {
		this.peopleService = peopleService;
		this.booksService = booksService;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", peopleService.findAll());
		return "people/index";
	}
	
	@GetMapping("/new")
	public String showNewPerson(@ModelAttribute("person") Person person) {
		return "people/new";
	}
	
	@PostMapping()
	public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "people/new";
		
		peopleService.save(person);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}")
	public String showPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", peopleService.findById(id));
		model.addAttribute("list", booksService.findBooksByPersonId(id));
		return "people/show";
	}
	
	@GetMapping("/{id}/edit")
	public String showEdit(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", peopleService.findById(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String editPerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "people/{id}/edit";
		}
		
		peopleService.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable("id") int id) {
		peopleService.delete(id);
		return "redirect:/people";
	}
}
