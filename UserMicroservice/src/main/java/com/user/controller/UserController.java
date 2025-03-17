package com.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entities.User;
import com.user.payload.ApiResponse;
import com.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user){
		if(user.getName()==null || user.getEmail()==null) {
			ApiResponse apiResponse=ApiResponse.builder().message("please send required details of user").success(false).httpStatus(HttpStatus.BAD_REQUEST).build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		}
		String userId=UUID.randomUUID().toString();
		user.setUserId(userId);
		User user2= userService.saveUser(user);
		return ResponseEntity.ok(user2);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") String userId){
		User user=userService.getUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	int count=0;
	@GetMapping
//	@CircuitBreaker(name = "ratingCircuitBreaker",fallbackMethod = "ratingCircuitBreaker")
//	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingCircuitBreaker")
	@Retry(name = "ratingCircuitBreaker",fallbackMethod = "ratingCircuitBreaker")
	public ResponseEntity<?> getAllUser(){
		count++;
		System.out.println("count: "+count);
		List<User> users=userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	@PutMapping("/{userId}")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody User user,@PathVariable("userId") String userId){
		User user2=userService.getUser(userId);
		user2.setAbout(user.getAbout());
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		userService.saveUser(user2);
		ApiResponse apiResponse=ApiResponse.builder().message("successfully changed").httpStatus(HttpStatus.ACCEPTED).success(true).build();
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String userId){
		userService.deleteUser(userId);
		ApiResponse apiResponse=ApiResponse.builder().message("successfully deleted").success(true).httpStatus(HttpStatus.OK).build();
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	public ResponseEntity<?> ratingCircuitBreaker(Exception exception){
		List<User> users=new ArrayList<>();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(users);
	}
}
