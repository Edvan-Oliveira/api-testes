package br.com.dicasdeumdev.api.services;

import java.util.List;

import br.com.dicasdeumdev.api.domain.User;

public interface UserService {

	User findById(Integer id);
	List<User> findAll();
}
