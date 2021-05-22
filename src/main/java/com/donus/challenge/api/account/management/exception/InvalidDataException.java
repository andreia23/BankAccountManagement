package com.donus.challenge.api.account.management.exception;

/**
 * @author andreia
 *
 */
public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 5861310537366287163L;
	
	public InvalidDataException() {
		super();
	}

	public InvalidDataException(final String message, final Throwable cause) {
		super(message, cause);

	}

	public InvalidDataException(final String message) {
		super(message);

	}

	public InvalidDataException(final Throwable cause) {
		super(cause);

	}
}
