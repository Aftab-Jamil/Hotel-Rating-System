package com.rating.exception;

public class IllegalHotelUserDetails extends RuntimeException {
	
	public IllegalHotelUserDetails() {
		super("please enter proper hotel id and user id");
		
	}
	
	public IllegalHotelUserDetails(String msg) {
		super(msg);
	}

}
