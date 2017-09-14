package com.cooksys.friendlr.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.friendlr.dto.PersonDto;
import com.cooksys.friendlr.entity.Person;
import com.cooksys.friendlr.mapper.PersonMapper;



@Service
public class PersonService {

	private Set<Person> personsList = new HashSet<Person>();
	
	private static Long count = 3L;
	
	private PersonMapper pMapper;
	
	public PersonService( PersonMapper pMapper) {
		
		 this.pMapper = pMapper;
		Person p1 = new Person( 1L, "Thanuja", "Reddy");
		personsList.add(p1);
		
		Person p2 = new Person( 2L, "Nithya", "Reddy");
		personsList.add(p2);		
	}

	public Set<PersonDto> getPersonList() {
		
		return personsList.stream().map(pMapper::toPersonDto).collect(Collectors.toSet());
	}

	public PersonDto getPersonId(Long id) {
		//Look for the id in the personsList
		
		for(Person p : personsList)
		{
			if(id.equals(p.getId() ) )
			{
				return pMapper.toPersonDto(p);
			}
		}
		return null;
	}

	public PersonDto createPerson(PersonDto p) {
		p.setId(count++); // count to set the id
		personsList.add(pMapper.toPerson(p));
		return p;
	}

	public PersonDto putPerson(Long id, PersonDto p2) {
		for(Person p : personsList)
		{
			if(id.equals(p.getId() ))
			{
				//Update the person firstName and lastName
				p.setFirstName(p2.getFirstName());
				p.setLastName(p2.getLastName());
				return pMapper.toPersonDto(p);
			}
		}
		return null;
	}


	//Delete the person with specified id and return the deleted person object, null otherwise
	public PersonDto deletePerson(Long id) {
		for(Person p : personsList)
		{
			if(p.getId() == id )
			{
				personsList.remove(p);
				return pMapper.toPersonDto(p);
			}
		}
		return null;
	}

	public PersonDto addFriend(Long id, Long frinedId) {
		PersonDto personDto = getPersonId(id);
		PersonDto frndDto = getPersonId(frinedId);
		getPersonEntity(id).getFriends().add(pMapper.toPerson(frndDto));
		getPersonEntity(frinedId).getFriends().add(pMapper.toPerson(personDto));
		return frndDto;
	}

	private Person getPersonEntity(Long id) {
		for(Person person : personsList)
		{
			if(person.getId().equals(id))
				
				return person;
		}
		
		return null;
	}

	

	public Set<PersonDto> getFriends(Long id) {
		if(getPersonEntity(id) == null)
			return null;
			else
				return getPersonEntity(id).getFriends().stream().map(pMapper::toPersonDto).collect(Collectors.toSet());
	}

}
