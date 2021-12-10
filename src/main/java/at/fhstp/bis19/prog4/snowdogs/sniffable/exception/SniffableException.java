package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableException extends Exception {
	public SniffableException() {
	}

	public SniffableException(String message) {
		super(message);
	}
	
	public SniffableException(Throwable cause) {
		super(cause);
	}

	public SniffableException(String message, Throwable cause) {
		super(message, cause);
	}
	
	//alternative @ResponeStatus
	public HttpStatus getHTTPStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
