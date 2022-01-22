package br.com.dicasdeumdev.api.resources.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {

	private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
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
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
	}
	
	@Test
	void whendataIntegrityViolationExceptionThenReturnAResponseEntity() {
		DataIntegratyViolationException e = new DataIntegratyViolationException(E_MAIL_JA_CADASTRADO_NO_SISTEMA);
		MockHttpServletRequest r = new MockHttpServletRequest();
		ResponseEntity<StandardError> response = exceptionHandler.dataIntegrityViolationException(e, r);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
		assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, response.getBody().getError());
		assertNotEquals("/user/2", response.getBody().getPath());
		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

}
