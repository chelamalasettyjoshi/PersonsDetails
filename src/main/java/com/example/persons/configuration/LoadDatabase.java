package com.example.persons.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.persons.model.Person;
import com.example.persons.repository.PersonRepository;

@Configuration
public class LoadDatabase {

	public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
	CommandLineRunner initDatabases(PersonRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Person("Aaa", "A Dept")));
			log.info("Preloading " + repository.save(new Person("Ppp", "P Dept")));
			log.info("Preloading " + repository.save(new Person("Bbb", "B Dept")));
			log.info("Preloading " + repository.save(new Person("Mmm", "M Dept")));
			log.info("Preloading " + repository.save(new Person("Ccc", "C Dept")));
			log.info("Preloading " + repository.save(new Person("Nnn", "N Dept")));
			log.info("Preloading " + repository.save(new Person("Ddd", "D Dept")));
			log.info("Preloading " + repository.save(new Person("Eee", "E Dept")));
			log.info("Preloading " + repository.save(new Person("Kkk", "K Dept")));
			log.info("Preloading " + repository.save(new Person("Fff", "F Dept")));
			log.info("Preloading " + repository.save(new Person("Jjj", "J Dept")));			
			log.info("Preloading " + repository.save(new Person("Lll", "L Dept")));			
			log.info("Preloading " + repository.save(new Person("Ooo", "O Dept")));			
			log.info("Preloading " + repository.save(new Person("Qqq", "Q Dept")));
		};
	}
}
