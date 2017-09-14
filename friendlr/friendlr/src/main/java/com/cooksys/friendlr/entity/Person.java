package com.cooksys.friendlr.entity;

import java.util.HashSet;
import java.util.Set;

public class Person {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Set<Person> friends = new HashSet<Person>();
		
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(Long id, String firstName, String lastName) {
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
	public Set<Person> getFriends() {
		return friends;
	}
	
	
	
	

}
