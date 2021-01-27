package com.dbo.api.exceptionhandler.exceptions;

public class UsuarioPermissionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3669334160889361771L;
	
	public UsuarioPermissionException(String message) {
		super(message);
	}
	
	public UsuarioPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

}
