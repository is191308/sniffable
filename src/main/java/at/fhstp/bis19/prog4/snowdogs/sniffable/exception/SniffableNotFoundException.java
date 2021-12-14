package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableNotFoundException extends SniffableException {
	public SniffableNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
