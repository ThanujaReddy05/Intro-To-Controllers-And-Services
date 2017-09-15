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

	private static Long count = 3L;
	private Set<Person> personsList = new HashSet<Person>();
	private PersonMapper pMapper;

	public PersonService( PersonMapper pMapper) {		
		this.pMapper = pMapper;
		Person p1 = new Person( 1L, "Thanuja", "Reddy");
		personsList.add(p1);
		Person p2 = new Person( 2L, "Nithya", "Reddy");
		personsList.add(p2);		
	}

	
	public Set<PersonDto> getPersonList() {
		//List is converted as stream, convert to Dto and then form set of the Dtos
		return personsList.stream().map(pMapper::toPersonDto).collect(Collectors.toSet());
	}

	
	public PersonDto getPersonId(Long id) {	
		for(Person p : personsList){   //Look for the id in the personsList
			if(id.equals(p.getId())){
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
		for(Person p : personsList){
			if(id.equals(p.getId() )){
				//Update the person firstName and lastName
				p.setFirstName(p2.getFirstName());
				p.setLastName(p2.getLastName());
				return pMapper.toPersonDto(p);
			}
		}
		return null;
	}


	
	//Delete the person with specified id and return the deleted person object, null otherwise
	public boolean deletePerson(Long id) {
		boolean status = false;
		for(Person p : personsList){
			if(id.equals(p.getId())){
				personsList.remove(p); 
				//Remove deleted person from the friends list of others
				personsList.forEach(person -> {
					if(person.getFriends().contains(p))
						person.getFriends().remove(p);
				}) ;
				 status = true;
				 return status;
			}
			
		}
		return status;
	}

	
	
	public boolean putFriend(Long id, Long frinedId) {
		Person person = getPersonEntity(id);
		Person frnd = getPersonEntity(frinedId);
		person.getFriends().add(frnd) ;
		frnd.getFriends().add(person);
		return true;
	}

	
	
	private Person getPersonEntity(Long id) {
		for(Person person : personsList){
			if(person.getId().equals(id))
				return person;
		}
		return null;
	}



	public Set<PersonDto> getFriends(Long id) {
		if(getPersonEntity(id) == null)
			return null;
		else
			//Person entity to stream, stream to dto and then to dto set 
			return getPersonEntity(id).getFriends().stream().map(pMapper::toPersonDto).collect(Collectors.toSet());
	}


	public boolean deleteFriend(Long id, Long frinedId) {
		Person p = getPersonEntity(id);
		Person f = getPersonEntity(frinedId);
		return (p.getFriends().remove(f) && (f.getFriends().remove(p)));
	}

}
