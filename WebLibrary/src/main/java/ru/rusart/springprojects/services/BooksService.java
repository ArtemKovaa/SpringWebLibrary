package ru.rusart.springprojects.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rusart.springprojects.models.Book;
import ru.rusart.springprojects.models.Person;
import ru.rusart.springprojects.repositories.BooksRepository;
import ru.rusart.springprojects.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class BooksService {
	private final BooksRepository booksRepository;
	private final PeopleRepository peopleRepository;
	
	@Autowired
	public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
		this.booksRepository = booksRepository;
		this.peopleRepository = peopleRepository;
	}
	
	public List<Book> findAll() {
		return booksRepository.findAll();
	}
	
	public List<Book> findAll(Optional<Integer> page, Optional<Integer> booksPerPage, Optional<Boolean> sortByYear) {
		if (page.isPresent() &&  booksPerPage.isPresent() && sortByYear.isPresent()) {
			return booksRepository.findAll(PageRequest.of(page.get(),booksPerPage.get(), Sort.by("yearOfWriting"))).getContent();
		}
		
		if (page.isPresent() &&  booksPerPage.isPresent()) {
			return booksRepository.findAll(PageRequest.of(page.get(),booksPerPage.get())).getContent();
		}
		
		if (sortByYear.isPresent()) {
			return booksRepository.findAll(Sort.by("yearOfWriting"));
		}
		
		return booksRepository.findAll();
	}
	
	@Transactional
	public void save(Book book) {
		booksRepository.save(book);
	}
	
	public Book findById(int id) {
		return booksRepository.findById(id).get();
	}
	
	public Person findReader(int id) {
		Book book = booksRepository.findById(id).get();
		return book.getReader();
	}
	
	public List<Book> findBooksByPersonId(int id) {
		Person person = peopleRepository.findById(id).get();
		Hibernate.initialize(person.getBooks());
		
		long delta;
		Date currentDate = new Date();
		for (Book book : person.getBooks()) {
			delta = (currentDate.getTime() - book.getTakenAt().getTime()) / 86400000;
			if (delta > 10) {
				book.setIsOverdued(true);
			}
			else {
				book.setIsOverdued(false);
			}
		}
		
		return person.getBooks();
	}
	
	@Transactional
	public void assign(int book_id, int person_id) {
		Book book = booksRepository.findById(book_id).get();
		book.setTakeAt(new Date());
		book.setReader(peopleRepository.findById(person_id).get());
	}
	
	@Transactional
	public void delete(int id) {
		booksRepository.deleteById(id);
	}
	
	@Transactional
	public void update(int id, Book book) {
		book.setBookId(id);
		booksRepository.save(book);
	}
	
	@Transactional
	public void free(int id) {
		Book book = booksRepository.findById(id).get();
		book.setReader(null);
		book.setTakeAt(null);
	}
	
	public List<Book> findByTitleStartingWith(Optional<String> startingWith) {
		if (startingWith.isPresent()) {
			return booksRepository.findByTitleStartingWith(startingWith.get());
		}
		
		return new ArrayList<Book>();
	}
}
