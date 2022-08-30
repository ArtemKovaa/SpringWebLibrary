package ru.rusart.springprojects.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class Book {
	private int bookId;
	
	@Pattern(regexp = "[A-Z].+", message = "Title should be in this format: Title")
	private String title;
	
	@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author name should be in this format: Author Name")
	private String author;
	
	@Min(value = 0, message = "Year of writing should be greater than 0")
	@Max(value = 2022, message = "Year of writing should be lower than 2022")
	private int yearOfWriting;
	
	public Book(int bookId, String title, String author, int yearOfWriting) {
		this.bookId = bookId;
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
}
