package com.rating.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.RatingMicroservice1Application;
import com.rating.entities.Rating;
import com.rating.payload.ApiResponse;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		List<Rating> ratings=new ArrayList<>();
		ratings=ratingService.getAllRatings();
		return new ResponseEntity<List<Rating>> (ratings,HttpStatus.OK);
	}
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getByRatingId(@PathVariable String ratingId){
		Rating rating=ratingService.getRatingById(ratingId);
		return new ResponseEntity<Rating>(rating,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveRating(@RequestBody Rating rating){
		rating.setRatingId(UUID.randomUUID().toString());
		Rating rating2=ratingService.saveRating(rating);
		return new ResponseEntity<Rating>(rating2,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingForUserId(@PathVariable String userId){
		List<Rating> lists=ratingService.getRatingByUserId(userId);
		return new  ResponseEntity<List<Rating>> (lists,HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingForHotelId(@PathVariable String hotelId){
		List<Rating> lists=ratingService.getRatingByhotelId(hotelId);
		return new  ResponseEntity<List<Rating>> (lists,HttpStatus.OK);
	}
	@DeleteMapping("/{ratingId}")
	public ResponseEntity<ApiResponse> deleteRating(@PathVariable String ratingId){
		ratingService.deleteRating(ratingId);
		ApiResponse response=ApiResponse.builder().message("deleted successfully").status(HttpStatus.OK).success(true).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping("/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable String ratingId,@RequestBody Rating rating){
		Rating savedRating=ratingService.getRatingById(ratingId);
		savedRating.setFeedback(rating.getFeedback());
		savedRating.setRating(rating.getRating());
		Rating rating2= ratingService.saveRating(savedRating);
		return new ResponseEntity<Rating>(rating2,HttpStatus.OK);
	}
 
}
