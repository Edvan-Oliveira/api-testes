package br.com.dicasdeumdev.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.UserService;
import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User create(UserDTO dto) {
		findByEmail(dto);
		return repository.save(mapper.map(dto, User.class));
	}
	
	@Override
	public User update(UserDTO dto) {
		findByEmail(dto);
		return repository.save(mapper.map(dto, User.class));
	}
	
	@Override
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	private void findByEmail(UserDTO dto) {
		Optional<User> user = repository.findByEmail(dto.getEmail());
		if (user.isPresent() && !user.get().getId().equals(dto.getId())) {
			throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
		}
	}
}
