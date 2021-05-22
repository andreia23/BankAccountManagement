package com.donus.challenge.api.account.management.exception;

/**
 * @author andreia
 *
 */
public class AccountNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 5861310537366287163L;
	
	public AccountNotFoundException(){
		super();
	}
	
	public AccountNotFoundException(String msg){
		super(msg);
	}
	
	public AccountNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
