package com.cooksys.friendlr.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.friendlr.entity.Person;

@Service
public class PersonService {

	private Set<Person> personsList = new HashSet<Person>();
	
	private static int count = 3;
	
	public PersonService() {
		Person p1 = new Person();
		p1.setId(1);
		p1.setFirstName("Thanuja");
		p1.setLastName("Reddy");

		personsList.add(p1);

		Person p2 = new Person();
		p2.setId(2);
		p2.setFirstName("Nithya");
		p2.setLastName("Reddy");

		personsList.add(p2);

	}

	public Set<Person> getPersonList() {
		
		return personsList;
	}

	public Person getPersonId(long id) {
		for(Person p : personsList)
		{
			if(p.getId() == id )
			{
				return p;
			}
		}
		return null;
	}

	public Person createPerson(Person p) {
		p.setId(count++);
		personsList.add(p);
		return p;
	}

	public Person updatePerson(long id, Person p2) {
		for(Person p : personsList)
		{
			if(p.getId() == id )
			{
				p.setFirstName(p2.getFirstName());
				p.setLastName(p2.getLastName());
				return p;
			}
		}
		return null;
	}

//	public boolean deletePerson(long id) {
//		boolean idFound = false;
//		for(Person p : personsList)
//		{
//			if(p.getId() == id )
//			{
//				personsList.remove(p);
//				idFound = true;
//			}
//		}
//		return idFound;
//	}

	public boolean deletePerson(long id) {
		boolean idFound = false;
		for(Person p : personsList)
		{
			if(p.getId() == id )
			{
				personsList.remove(p);
				idFound = true;
			}
		}
		return idFound;
	}

}
