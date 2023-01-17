package com.example.persons.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.persons.model.Person;

public interface PersonPageAndSortRepository extends PagingAndSortingRepository<Person, Integer> {

}
