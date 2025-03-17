package com.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.payload.ApiResponse;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(HotelNotFound.class)
	public ResponseEntity<ApiResponse> hotelNotFound(HotelNotFound hotelNotFound){
		ApiResponse response=ApiResponse.builder().message(hotelNotFound.getMessage()).status(HttpStatus.NOT_FOUND).success(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> IllegaArgument(IllegalArgumentException ex){
		ApiResponse response=ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}

}
