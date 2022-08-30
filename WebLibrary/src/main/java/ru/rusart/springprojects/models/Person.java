package ru.rusart.springprojects.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class Person {
	private int personId;
	
	@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: Name Surname Middlename")
	private String fullName;
	
	@Min (value = 1800, message = "Year of birth should be greater than 1800")
	@Max (value = 2022, message = "Year of birth should be less than 2022")
	private int yearOfBirth;

	public Person(int personId, String fullName, int yearOfBirth) {
		this.personId = personId;
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
	}
	
	public Person() { }
	
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
}
