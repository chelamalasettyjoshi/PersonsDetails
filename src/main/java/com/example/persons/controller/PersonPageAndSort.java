package com.example.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.persons.model.Person;
import com.example.persons.repository.PersonPageAndSortRepository;

@RestController
public class PersonPageAndSort {
	
	@Autowired
	PersonPageAndSortRepository personPageAndSortRepository;
	
	@GetMapping("/personsdetails/")
	public ResponseEntity<List<Person>> getAllPerson(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Person> pagedResult = personPageAndSortRepository.findAll(paging);
		List<Person> list = pagedResult.getContent();

		return new ResponseEntity<List<Person>>(list, new HttpHeaders(), HttpStatus.OK);
	}
}
