package at.fhstp.bis19.prog4.snowdogs.sniffable.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SniffableNotFoundException extends SniffableException {
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
	
	@Override
	public HttpStatus getHTTPStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
}
