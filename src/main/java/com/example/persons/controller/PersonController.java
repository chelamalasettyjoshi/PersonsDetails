package com.example.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.persons.model.Person;
import com.example.persons.repository.PersonPageAndSortRepository;
import com.example.persons.repository.PersonRepository;

@RestController
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/persons/")
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public Person getPerson(@PathVariable int id) {
		return personRepository.findById(id).get();
	}

	@PostMapping("/persons/")
	public Person createPerson(@RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/persons/")
	public Person updatePerson(@RequestBody Person person) {
		return personRepository.save(person);
	}

	@DeleteMapping("/persons/{id}")
	public void deletePerson(@PathVariable int id) {
		personRepository.deleteById(id);
	}

}
