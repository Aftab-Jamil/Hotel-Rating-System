package com.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.payload.ApiResponse;

@RestControllerAdvice
public class GolobalExceptionHandler {
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ApiResponse> userNotFound(UserNotFound userNotFound){
			ApiResponse apiResponse=ApiResponse.builder().message(userNotFound.getMessage()).httpStatus(HttpStatus.NOT_FOUND).success(false).build();
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}

}
