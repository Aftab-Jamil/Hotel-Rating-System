package com.rating.exception;

public class RatingNotFound extends RuntimeException{

	public RatingNotFound() {
		super("Rating Not found!!!");
	}
	public RatingNotFound(String msg) {
		super(msg);
	}
}
