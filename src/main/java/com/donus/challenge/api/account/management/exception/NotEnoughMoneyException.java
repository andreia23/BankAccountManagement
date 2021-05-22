package com.donus.challenge.api.account.management.exception;

/**
 * @author andreia
 *
 */
public class NotEnoughMoneyException extends RuntimeException {

	private static final long serialVersionUID = 5861310537366287163L;

	public NotEnoughMoneyException() {
		super();
	}

	public NotEnoughMoneyException(final String message, final Throwable cause) {
		super(message, cause);

	}

	public NotEnoughMoneyException(final String message) {
		super(message);

	}

	public NotEnoughMoneyException(final Throwable cause) {
		super(cause);

	}
}
