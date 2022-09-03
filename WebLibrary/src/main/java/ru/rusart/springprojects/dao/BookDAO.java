package ru.rusart.springprojects.dao;


import org.springframework.stereotype.Component;

@Component
public class BookDAO {
	
/*	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
	}
	
	@SuppressWarnings("deprecation")
	public List<Book> getBooks(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[] {id}, new BeanPropertyRowMapper<>(Book.class));
	}

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO Book(title, author, year_of_writing) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYearOfWriting());
	}
	
	@SuppressWarnings("deprecation")
	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[] {id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
	}
	
	@SuppressWarnings("deprecation")
	public Person showReader(int id) {
		return jdbcTemplate.query("SELECT * FROM Person JOIN Book ON Person.person_id = Book.person_id WHERE book_id = ?", 
				new Object[] {id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}
	
	public void assign(int bookId, int personId) {
		jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", personId, bookId);
	}
	
	public void free(int book_id) {
		jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id = ?", book_id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
	}
	
	public void update(int id, Book book) {
		jdbcTemplate.update("UPDATE Book SET title = ?, author = ?, year_of_writing = ? WHERE book_id = ?", book.getTitle(), book.getAuthor(), book.getYearOfWriting(), id);
	}
	
*/
}
