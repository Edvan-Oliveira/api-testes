package br.com.dicasdeumdev.api.resources.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;
	
	@BeforeEach
	void setUp() {
		openMocks(this);
	}

	@Test
	void whenbjectNotFoundExceptionThenReturnAResponseEntity() {
		ObjectNotFoundException e = new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO);
		MockHttpServletRequest r = new MockHttpServletRequest();
		ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(e, r);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}

}
