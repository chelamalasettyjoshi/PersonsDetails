package com.example.persons;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.persons.model.Person;
import com.example.persons.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/*If you want to focus only on the web layer and not start a complete
ApplicationContext, consider using @WebMvcTest instead.*/
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class PersonsDetailsApplicationTests {

	private static final String PERSON_URL = "/persons/";
	private static final String PERSON_DEPARTMENT = "J Dept";
	private static final String PERSON_NAME = "Jjj";
	private static final int PERSON_ID = 1;
	@MockBean
	PersonRepository personRepository;
	@Autowired
	MockMvc mockMvc;

// mockMvc object should load from WebApplicationContext in before program run
//	@Autowired
//	private WebApplicationContext wac;
//	
//	@BeforeEach
//	public void setup() {
//		mockMvc= MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
//	}

	@Test
	@WithMockUser
	void testgetPerson() throws Exception {
		Person person = buildPerson();
		List<Person> aslist = Arrays.asList(person);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		when(personRepository.findAll()).thenReturn(aslist);
		mockMvc.perform(get(PERSON_URL)).andExpect(status().isOk())
				.andExpect(content().json(objectWriter.writeValueAsString(aslist)));
	}

	@Test
	@WithMockUser
	void testCreatePerson() throws JsonProcessingException, Exception {
		Person person = buildPerson();
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		when(personRepository.save(any())).thenReturn(person);
		mockMvc.perform(post(PERSON_URL).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON).content(objectWriter.writeValueAsString(person)))
				.andExpect(status().isOk()).andExpect(content().json(objectWriter.writeValueAsString(person)));
	}

	@Test
	@WithMockUser
	void testUpdatePerson() throws JsonProcessingException, Exception {
		Person person = buildPerson();
		person.setName("Sss");
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		when(personRepository.save(any())).thenReturn(person);
		mockMvc.perform(put(PERSON_URL).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON).content(objectWriter.writeValueAsString(person)))
				.andExpect(status().isOk()).andExpect(content().json(objectWriter.writeValueAsString(person)));
	}

	@Test
	@WithMockUser
	void testDeletePerson() throws Exception {
		doNothing().when(personRepository).deleteById(PERSON_ID);
		mockMvc.perform(delete(PERSON_URL + PERSON_ID).with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	void testGetSinglePerson() throws Exception {
		Person person = buildPerson();
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		when(personRepository.findById(any())).thenReturn(Optional.of(person));
		mockMvc.perform(get(PERSON_URL + PERSON_ID)).andExpect(status().isOk())
				.andExpect(content().json(objectWriter.writeValueAsString(person)));
	}

	private Person buildPerson() {
		Person person = new Person();
		person.setId(PERSON_ID);
		person.setName(PERSON_NAME);
		person.setDepartment(PERSON_DEPARTMENT);
		return person;
	}

	@Test
	void testLoginPage() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

	@Test
	void indexWhenUnAuthenticatedThenRedirect() throws Exception {
		mockMvc.perform(get("/persons/")).andExpect(status().isUnauthorized());
	}

}
