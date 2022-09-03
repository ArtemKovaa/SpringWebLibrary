package ru.rusart.springprojects.dao;

import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
	
/*	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Person> index() {
		return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
	}
	
	public void save(Person person) {
		jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES (?, ?)", person.getFullName(), person.getYearOfBirth());
	}
	
	@SuppressWarnings("deprecation")
	public Person showPerson(int id) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}
	
	public void update(int id, Person person) {
		jdbcTemplate.update("UPDATE Person SET full_name = ?, year_of_birth = ? WHERE person_id = ?", person.getFullName(), person.getYearOfBirth(), id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", id);
	}
*/
}
