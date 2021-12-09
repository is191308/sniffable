package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableNotFoundException extends SniffableGeneralException {
	public final HttpStatus httpErrorCode = HttpStatus.NOT_FOUND;

	public SniffableNotFoundException() {
	}

	public SniffableNotFoundException(String message) {
		super(message);
	}
	
	public SniffableNotFoundException(Throwable cause) {
		super(cause);
	}

	public SniffableNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
