package br.com.dicasdeumdev.api.resources;

import static org.junit.jupiter.api.Assertions.*;

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
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}
}
