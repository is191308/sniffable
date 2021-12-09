package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableIllegalValueException extends SniffableGeneralException {
	public final HttpStatus httpErrorCode = HttpStatus.BAD_REQUEST;

	public SniffableIllegalValueException() {
	}

	public SniffableIllegalValueException(String message) {
		super(message);
	}
	
	public SniffableIllegalValueException(Throwable cause) {
		super(cause);
	}

	public SniffableIllegalValueException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
