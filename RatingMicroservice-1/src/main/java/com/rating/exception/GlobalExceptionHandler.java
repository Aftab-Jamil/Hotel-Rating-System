package com.rating.exception;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rating.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RatingNotFound.class)
	public ResponseEntity<ApiResponse> ratingNotFound(RatingNotFound ex){
		ApiResponse response=ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalHotelUserDetails.class)
	public ResponseEntity<ApiResponse> ratingNotFound(IllegalHotelUserDetails ex){
		ApiResponse response=ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalRatingException.class)
	public ResponseEntity<ApiResponse> ratingNotFound(IllegalRatingException ex){
		ApiResponse response=ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}

}
