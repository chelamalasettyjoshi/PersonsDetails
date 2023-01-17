package com.example.persons.integTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.example.persons.model.Person;
import com.example.persons.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SpringBootTest
class PersonsDetailsApplicationTests1 {

	@Autowired
	PersonRepository repository;
	RestTemplate restTemplate = new RestTemplate();

	@Test
	void contextLoads() {
		List<Person> li = repository.findAll();
		assertNotNull(li);
		assertEquals("Aaa", li.get(0).getName());
	}

	// creating REST Client using RestTemplate

	@Test
	void testGetPerson() {
		Person person = restTemplate.getForObject("http://localhost:8080/persons/1", Person.class);
		assertNotNull(person);
		assertEquals("Ddd", person.getName());
	}

	@Test
	void testCreatePerson() {
		Person person = new Person();
		person.setName("Ccc");
		person.setDepartment("C Dept");
		Person person2 = restTemplate.postForObject("http://localhost:8080/persons/", person, Person.class);
		assertNotNull(person2);
		assertEquals("Ccc", person2.getName());
	}
	
	@Test
	void testUpdatePerson() {
		Person person = restTemplate.getForObject("http://localhost:8080/persons/1", Person.class);
		person.setName("Ddd");
		restTemplate.put("http://localhost:8080/persons/", person);	
		Person person2 = restTemplate.getForObject("http://localhost:8080/persons/1", Person.class);
		assertEquals("Ddd", person2.getName());
	}

}
