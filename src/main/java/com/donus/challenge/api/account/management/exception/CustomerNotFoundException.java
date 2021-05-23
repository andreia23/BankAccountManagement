package com.donus.challenge.api.account.management.exception;

/**
 * @author andreia
 *
 */
public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5861310537366287163L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String msg) {
		super(msg);
	}

	public CustomerNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
