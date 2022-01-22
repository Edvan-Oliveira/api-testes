package br.com.dicasdeumdev.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Valdir", "valdir@mail.com", "123");
		User u2 = new User(null, "Luiz", "luiz@mail.com", "123");
		
		repository.saveAll(List.of(u1, u2));
	}

}
