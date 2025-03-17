package com.user.payload;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Data
public class ApiResponse {
	private String message;
	private HttpStatus httpStatus;
	private boolean success;
	

}
