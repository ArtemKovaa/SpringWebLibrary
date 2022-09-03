package ru.rusart.springprojects.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rusart.springprojects.models.Person;
import ru.rusart.springprojects.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {
	private final PeopleRepository peopleRepository;
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}
	
	public List<Person> findAll() {
		return peopleRepository.findAll();
	}
	
	@Transactional
	public void save(Person person) {
		peopleRepository.save(person);
	}
	
	@Transactional
	public void delete(int id) {
		peopleRepository.deleteById(id);
	}
	
	@Transactional
	public void update(int id, Person person) {
		person.setPersonId(id);
		peopleRepository.save(person);
	}
	
	public Person findById(int id) {
		return peopleRepository.findById(id).get();
	}
	
	
}
