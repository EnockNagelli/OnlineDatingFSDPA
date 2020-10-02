package com.iiht.dating.exception;

public class InvalidProfileException extends RuntimeException {

	private static final long serialVersionUID = 5496839376367199345L;

	public InvalidProfileException(String message) {
		super(message);
	}
}