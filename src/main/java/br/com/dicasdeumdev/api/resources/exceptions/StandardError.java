package br.com.dicasdeumdev.api.resources.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StandardError {
	private LocalDateTime timestamp	;
	private Integer status;
	private String error;
	private String path;
}
