package ru.rusart.springprojects.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int bookId;
	
	@Pattern(regexp = "[A-Z].+", message = "Title should be in this format: Title")
	@Column(name = "title")
	private String title;
	
	@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author name should be in this format: Author Name")
	@Column(name = "author")
	private String author;
	
	@Min(value = 0, message = "Year of writing should be greater than 0")
	@Max(value = 2022, message = "Year of writing should be lower than 2022")
	@Column(name = "year_of_writing")
	private int yearOfWriting;
	
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "person_id")
	private Person reader;
	
	@Column(name = "taken_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date takenAt;
	
	@Transient
	private boolean isOverdued;
	
	public Book(String title, String author, int yearOfWriting) {
		this.title = title;
		this.author = author;
		this.yearOfWriting = yearOfWriting;
	}
	
	public Book() { }
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYearOfWriting() {
		return yearOfWriting;
	}
	
	public void setYearOfWriting(int yearOfWriting) {
		this.yearOfWriting = yearOfWriting;
	}
	
	public Person getReader() {
		return reader;
	}
	
	public void setReader(Person reader) {
		if (this.reader != null) {
			this.reader.getBooks().remove(this);
			this.reader = reader;
		}
		else {
			this.reader = reader;
			reader.getBooks().add(this);
		}
		
	}
	
	public Date getTakenAt() {
		return takenAt;
	}
	
	public void setTakeAt(Date takenAt) {
		this.takenAt = takenAt;
	}
	
	public boolean getIsOverdued() {
		return isOverdued;
	}
	
	public void setIsOverdued(boolean isOverdued) {
		this.isOverdued = isOverdued;
	}
}
