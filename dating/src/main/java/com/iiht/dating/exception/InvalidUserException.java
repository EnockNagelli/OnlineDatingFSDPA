package com.iiht.dating.exception;

public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = -5869274351353193564L;

	public InvalidUserException(String message) {
		super(message);
	}
}