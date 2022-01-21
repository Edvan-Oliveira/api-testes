package br.com.dicasdeumdev.api.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.services.UserService;

@SpringBootTest
class UserResourceTest {
	
	private static final int INDEX = 0;
	private static final Integer ID = 1;
	private static final String NAME = "Valdir";
	private static final String EMAIL = "valdir@mail.com";
	private static final String PASSWORD = "123";
	
	@InjectMocks
	private UserResource resource;
	
	@Mock
	private UserService service;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;
	
	@BeforeEach
	void setUp() {
		openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		UserDTO body = response.getBody();
		assertEquals(ID, body.getId());
		assertEquals(NAME, body.getName());
		assertEquals(EMAIL, body.getEmail());
		assertEquals(PASSWORD, body.getPassword());
	}
	
	@Test
	void whenFindAllThenReturnAListOfUserDTO() {
		when(service.findAll()).thenReturn(List.of(user));
		when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);
		
		ResponseEntity<List<UserDTO>> response = resource.findAll();
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		
		List<UserDTO> body = response.getBody();
		assertFalse(body.isEmpty());
		assertEquals(1, body.size());
		
		assertNotNull(body.get(INDEX));
		assertEquals(UserDTO.class, body.get(INDEX).getClass());
		assertEquals(ID, body.get(INDEX).getId());
		assertEquals(NAME, body.get(INDEX).getName());
		assertEquals(EMAIL, body.get(INDEX).getEmail());
		assertEquals(PASSWORD, body.get(INDEX).getPassword());
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}
}
