package com.hotel.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.entities.Hotel;
import com.hotel.entities.Rating;

@FeignClient("RATINGMICROSERVICE-1")
public interface RatingClientService {
	
	@GetMapping("/ratings/hotel/{hotelId}")
	List<Rating> getAllRatingsByHotelId(@PathVariable String hotelId);
	

}
