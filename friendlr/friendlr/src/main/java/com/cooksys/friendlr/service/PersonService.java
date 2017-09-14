package com.cooksys.friendlr.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.friendlr.entity.Person;

@Service
public class PersonService {

	private Set<Person> personsList = new HashSet<Person>();
	
	private static Long count = 3L;
	
	public PersonService() {
		Person p1 = new Person( 1L, "Thanuja", "Reddy");
		personsList.add(p1);
		
		Person p2 = new Person( 2L, "Nithya", "Reddy");
		personsList.add(p2);		
	}

	public Set<Person> getPersonList() {
		
		return personsList;
	}

	public Person getPersonId(Long id) {
		//Look for the id in the personsList
		
		for(Person p : personsList)
		{
			if(id.equals(p.getId() ) )
			{
				return p;
			}
		}
		return null;
	}

	public Person createPerson(Person p) {
		p.setId(count++); // count to set the id
		personsList.add(p);
		return p;
	}

	public Person putPerson(Long id, Person p2) {
		for(Person p : personsList)
		{
			if(id.equals(p.getId() ))
			{
				//Update the person firstName and lastName
				p.setFirstName(p2.getFirstName());
				p.setLastName(p2.getLastName());
				return p;
			}
		}
		return null;
	}


	//Delete the person with specified id and return the deleted person object, null otherwise
	public Person deletePerson(Long id) {
		for(Person p : personsList)
		{
			if(p.getId() == id )
			{
				personsList.remove(p);
				return p;
			}
		}
		return null;
	}

}
