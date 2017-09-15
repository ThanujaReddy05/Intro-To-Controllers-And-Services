package com.cooksys.friendlr.controller;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.friendlr.dto.PersonDto;
import com.cooksys.friendlr.service.PersonService;


	//Controller deals with only Dtos. Entities are never used in controller 

@RestController
@RequestMapping("person")
public class PersonController {

	private PersonService pService;	

	//Dependency injection: Injecting controller dependency on service By placing service object in controller constructor  
	
	public PersonController(PersonService service)
	{
		this.pService = service; 
	}


	//Returns all the entity Dtos
	
	@GetMapping
	public Set<PersonDto> getAll()
	{
		return pService.getPersonList();
	}


	//Returns entity Dto of the specified id, set status message accordingly
	
	@GetMapping("{id}")
	public PersonDto getPersonId(@PathVariable Long id, HttpServletResponse response)
	{   
		PersonDto foundP = pService.getPersonId(id);
		if(foundP == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}	
		else
		{	
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		return foundP;

	}


	//Creates a new entity from the JSON object and returns the new entity's Dto and also sets the status message accordingly
	
	@PostMapping
	public PersonDto createPerson(@RequestBody PersonDto p,HttpServletResponse response)
	{
		PersonDto postPerson = pService.createPerson(p);
		if(postPerson != null)
		{
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return postPerson;
	}


	//Updates the entity of specified id and returns the Dto of the same. And sets the status message accordingly 
	
	@PutMapping("{id}")
	public PersonDto updatePerson(@PathVariable Long id,@RequestBody PersonDto p, HttpServletResponse response)
	{

		PersonDto updatedPerson = pService.putPerson(id,p);
		if(updatedPerson == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		return updatedPerson;
	}

	
	//This method deletes the specific id(and its corresponding entity) and returns  the deleted Dto. Sets the status message.  
	
	@DeleteMapping("{id}")
	public void deletePerson(@PathVariable Long id,HttpServletResponse response)
	{
		boolean found = pService.deletePerson(id);
		if(found)
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);			
		}
	}

	
	//Adds friends to the specific id
	
	@PutMapping("{id}/friend/{friendId}")
	public void addFriend(@PathVariable Long id, @PathVariable Long friendId, HttpServletResponse response)
	{
		boolean status = pService.putFriend(id, friendId);
		if(status)
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	
	//Returns the list of  friends of the given id.
	
	@GetMapping("{id}/friend")
	public Set<PersonDto> getFriends(@PathVariable Long id, HttpServletResponse response){
		Set<PersonDto> frndList = pService.getFriends(id);

		if(frndList  == null )
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		return frndList;
	}
	
	
	@DeleteMapping("{id}/friend/{friendId}")
	public void deleteFriend(@PathVariable Long id,@PathVariable Long friendId, HttpServletResponse response)
	{
		boolean found = pService.deleteFriend(id, friendId);
		if(found)
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}


}


