package com.cooksys.friendlr.controller;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Set<Person> getAll()
	{
		return pService.getPersonList();
	}
	
	@GetMapping("{id}")
	public Person getPersonId(@PathVariable Long id, HttpServletResponse response)
	{   
		Person foundP = (Person) pService.getPersonId(id);
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
	public Person createPerson(@RequestBody Person p,HttpServletResponse response)
	{
		Person postPerson = pService.createPerson(p);
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
	public Person updatePerson(@PathVariable Long id,@RequestBody Person p, HttpServletResponse response)
	{
		System.out.println("IN put");
		Person updatedPerson = pService.putPerson(id,p);
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
	public Person deletePerson(@PathVariable Long id,HttpServletResponse response)
	{
		Person found = pService.deletePerson(id);
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
}

