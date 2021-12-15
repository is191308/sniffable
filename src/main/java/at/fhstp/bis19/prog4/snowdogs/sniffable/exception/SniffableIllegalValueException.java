package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableIllegalValueException extends SniffableException {
	public SniffableIllegalValueException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
