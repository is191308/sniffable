package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableAlreadyExistsException extends SniffableException {
	public SniffableAlreadyExistsException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}
