package com.iiht.dating.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -4604728509800765405L;

	public UserNotFoundException(String message) {
		super(message);
	}
}