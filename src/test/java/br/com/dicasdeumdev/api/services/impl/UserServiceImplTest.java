package br.com.dicasdeumdev.api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {

	private static final int INDEX = 0;
	private static final Integer ID = 1;
	private static final String NAME = "Valdir";
	private static final String EMAIL = "valdir@mail.com";
	private static final String PASSWORD = "123";

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private ModelMapper mapper;

	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);

		User response = service.findById(ID);
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}

	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));

		/*
		 * when(repository.findById(anyInt())).thenThrow(new
		 * ObjectNotFoundException("Objeto não encontrado")); try {
		 * service.findById(ID); } catch (Exception e) {
		 * assertEquals(ObjectNotFoundException.class, e.getClass());
		 * assertEquals("Objeto não encontrado", e.getMessage()); }
		 */
	}

	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		when(repository.findAll()).thenReturn(List.of(user));

		List<User> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(INDEX).getClass());

		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NAME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(PASSWORD, response.get(INDEX).getPassword());

		// assertThat(response).isNotNull().isNotEmpty().hasSize(1);
	}

	@Test
	void whenCreteThenReturnSuccess() {
		when(repository.save(any())).thenReturn(user);

		User response = service.create(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}

	@Test
	void whenCreteThenReturnAnDataIntegratyViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(optionalUser);
		userDTO.setId(2);
		Exception e = assertThrows(DataIntegratyViolationException.class, () -> service.create(userDTO));
		assertEquals("E-mail já cadastrado no sistema", e.getMessage());
	}

	@Test
	void whenUpdateThenReturnSuccess() {
		when(repository.save(any())).thenReturn(user);
		
		User response = service.update(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}
	
	@Test
	void testDelete() {
	}

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}

}
