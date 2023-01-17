package com.example.persons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.persons.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
