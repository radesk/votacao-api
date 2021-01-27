package com.dbo.api.exceptionhandler.exceptions;

public class PautaStatusException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3669334160889361771L;
	
	public PautaStatusException(String message) {
		super(message);
	}
	
	public PautaStatusException(String message, Throwable cause) {
		super(message, cause);
	}

}
