package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
public class SniffableException extends ResponseStatusException {

	public SniffableException(String message) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
	
	public SniffableException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}
}
