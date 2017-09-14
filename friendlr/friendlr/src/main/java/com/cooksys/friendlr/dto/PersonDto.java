package com.cooksys.friendlr.dto;

import java.util.HashSet;
import java.util.Set;

import com.cooksys.friendlr.entity.Person;

public class PersonDto {

	private Long id;
	private String firstName;
	private String lastName;
	private Set<Person> friends = new HashSet<>();
	
		
	public PersonDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonDto(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
