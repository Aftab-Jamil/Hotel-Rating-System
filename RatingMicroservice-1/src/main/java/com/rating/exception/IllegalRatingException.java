package com.rating.exception;

public class IllegalRatingException extends RuntimeException {
	
	public IllegalRatingException() {
		super("Please Enter rating between 0-5");		
	}
	
	public IllegalRatingException(String msg) {
		super(msg);
	}

}
