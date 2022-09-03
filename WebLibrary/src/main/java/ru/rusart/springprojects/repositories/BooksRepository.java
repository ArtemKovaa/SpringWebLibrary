package ru.rusart.springprojects.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.rusart.springprojects.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
	public List<Book> findByTitleStartingWith(String startingWith);
}
