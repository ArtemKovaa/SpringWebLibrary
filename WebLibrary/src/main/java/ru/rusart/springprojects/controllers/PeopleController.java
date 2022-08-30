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

import ru.rusart.springprojects.dao.BookDAO;
import ru.rusart.springprojects.dao.PersonDAO;
import ru.rusart.springprojects.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

	private final PersonDAO personDAO;
	private final BookDAO bookDAO;
	
	@Autowired
	public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
		this.personDAO = personDAO;
		this.bookDAO = bookDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", personDAO.index());
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
		
		personDAO.save(person);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}")
	public String showPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.showPerson(id));
		model.addAttribute("list", bookDAO.getBooks(id));
		return "people/show";
	}
	
	@GetMapping("/{id}/edit")
	public String showEdit(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.showPerson(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String editPerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "people/{id}/edit";
		}
		
		personDAO.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}
}
