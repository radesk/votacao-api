package com.dbo.api.exceptionhandler.exceptions;

public class PautaTimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3669334160889361771L;
	
	public PautaTimeException(String message) {
		super(message);
	}
	
	public PautaTimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
