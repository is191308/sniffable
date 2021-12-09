package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableAlreadyExistsException extends SniffableGeneralException {
	public final HttpStatus httpErrorCode = HttpStatus.CONFLICT;
	
	public SniffableAlreadyExistsException() {
	}

	public SniffableAlreadyExistsException(String message) {
		super(message);
	}
	
	public SniffableAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public SniffableAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
