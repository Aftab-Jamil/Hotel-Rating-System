package com.hotel.exceptions;

public class HotelNotFound extends RuntimeException{
	
	public HotelNotFound() {
		super("oops hotel not found!!!");
	}
	public HotelNotFound(String message) {
		super(message);
	}

}
