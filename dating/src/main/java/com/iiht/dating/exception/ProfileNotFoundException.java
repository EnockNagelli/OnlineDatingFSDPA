package com.iiht.dating.exception;

public class ProfileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2060771567263195185L;

	public ProfileNotFoundException(String message) {
		super(message);
	}
}