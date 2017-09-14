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
import com.cooksys.friendlr.entity.Person;
import com.cooksys.friendlr.service.PersonService;




@RestController
@RequestMapping("person")
public class PersonController {
	
	private PersonService pService;
	
	public PersonController(PersonService service)
	{
		this.pService = service; 
	}
	

	@GetMapping
	public Set<PersonDto> getAll()
	{
		return pService.getPersonList();
	}
	
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
	
	@DeleteMapping("{id}")
	public PersonDto deletePerson(@PathVariable Long id,HttpServletResponse response)
	{
		PersonDto found = pService.deletePerson(id);
		if(found == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		
		  return found;
	}
	
	@PutMapping("{id}/friend/{friendId}")
	public PersonDto addFriend(@PathVariable Long id, @PathVariable Long friendId,HttpServletResponse response)
	{
		PersonDto frnd = pService.addFriend(id, friendId);
		if(frnd  == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		return frnd;
		
		
	}
	
	@GetMapping("{id}/friend")
	public Set<PersonDto> getFriends(@PathVariable Long id,HttpServletResponse response){
		Set<PersonDto> frndList = pService.getFriends(id);
		
		if(frndList  == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		return frndList;
	}
	
	
}


