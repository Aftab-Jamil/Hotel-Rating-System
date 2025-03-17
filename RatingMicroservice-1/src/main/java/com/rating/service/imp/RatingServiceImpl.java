package com.rating.service.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rating.entities.Hotel;
import com.rating.entities.Rating;
import com.rating.exception.IllegalHotelUserDetails;
import com.rating.exception.IllegalRatingException;
import com.rating.exception.RatingNotFound;
import com.rating.external.service.HotelClientService;
import com.rating.payload.ApiResponse;
import com.rating.repositories.RatingRepository;
import com.rating.service.RatingService;
@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelClientService hotelClientService;
	@Override
	public Rating saveRating(Rating rating) {
		if(rating.getRating()<0 || rating.getRating()>5) {
			throw new IllegalRatingException("Please Enter Rating between 0-5");
		}
		if(rating.getHotelId().isBlank()) {
			throw new IllegalHotelUserDetails("Please Enter proper hotel id"); 
		}
		if(rating.getUserId().isBlank()) {
			throw new IllegalHotelUserDetails("Please Enter proper User id"); 
		}
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		List<Rating> ratings=ratingRepository.findAll();
		return ratings;
	}

	@Override
	public Rating getRatingById(String ratingId) {
		Rating rating=ratingRepository.findById(ratingId).orElseThrow(()->new RatingNotFound("Rating with id: "+ ratingId +" not found"));
		return rating;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		List<Rating> ratings=ratingRepository.findByUserId(userId);
		List<Rating> ratings2= ratings.stream().map(rating->{
//			Hotel hotel=restTemplate.getForObject("http://HOTELMICROSERVICE/hotel/"+rating.getHotelId(), Hotel.class);
//			rating.setHotel(hotel);
			Hotel hotel=hotelClientService.getHotelByHotelId(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		return ratings2;
	}

	@Override
	public List<Rating> getRatingByhotelId(String hotelId) {
		List<Rating> ratings=ratingRepository.findByHotelId(hotelId);
		return ratings;
	}

	@Override
	public void deleteRating(String ratingId) {
		Rating rating=getRatingById(ratingId);
		ratingRepository.delete(rating);
		
	}

}
