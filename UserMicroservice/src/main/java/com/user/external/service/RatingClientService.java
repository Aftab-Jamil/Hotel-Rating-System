package com.user.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("RATINGMICROSERVICE-1")
public interface RatingClientService {
	@GetMapping("/ratings/user/{userId}")
	List<com.user.entities.Rating> getAllRatingsByUserId(@PathVariable String userId);
	
	
}
