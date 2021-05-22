package com.donus.challenge.api.account.management.exception;

/**
 * @author andreia
 *
 */
public class DuplicateDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateDataException() {
		super();
	}

	public DuplicateDataException(final String message, final Throwable cause) {
		super(message, cause);

	}

	public DuplicateDataException(final String message) {
		super(message);

	}

	public DuplicateDataException(final Throwable cause) {
		super(cause);

	}

	
}
