package com.rating.service;

import java.util.List;

import com.rating.entities.Rating;

public interface RatingService {
	
	public Rating saveRating(Rating rating);
	
	public List<Rating> getAllRatings();
	
	public Rating getRatingById(String ratingId);
	
	public List<Rating> getRatingByUserId(String userId);
	
	public List<Rating> getRatingByhotelId(String hotelId);
	
	public void deleteRating(String ratingId);
	

}
