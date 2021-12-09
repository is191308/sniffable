package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableGeneralException extends Exception {
	public final HttpStatus httpErrorCode = HttpStatus.INTERNAL_SERVER_ERROR;

	public SniffableGeneralException() {
	}

	public SniffableGeneralException(String message) {
		super(message);
	}
	
	public SniffableGeneralException(Throwable cause) {
		super(cause);
	}

	public SniffableGeneralException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
