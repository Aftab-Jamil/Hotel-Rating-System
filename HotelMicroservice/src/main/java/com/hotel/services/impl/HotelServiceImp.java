package com.hotel.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotel.entities.Hotel;
import com.hotel.entities.Rating;
import com.hotel.exceptions.HotelNotFound;
import com.hotel.external.service.RatingClientService;
import com.hotel.repositories.HotelRepository;
import com.hotel.services.HotelService;
@Service
public class HotelServiceImp implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RatingClientService ratingClientService;
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		if(hotel.getLocation().isBlank() || hotel.getName().isBlank() || hotel==null ) {
			throw new IllegalArgumentException("please enter all the neccessay fields");
		}
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new HotelNotFound("Hotel with id : '"+hotelId+"' not found"));
		// using restTemplate
//		Rating[] ratings=restTemplate.getForObject("http://RATINGMICROSERVICE-1/ratings/hotel/"+hotelId,Rating [].class);
//		hotel.setRatings(Arrays.asList(ratings));
		//using feign client
		List<Rating> ratings=ratingClientService.getAllRatingsByHotelId(hotelId);
		hotel.setRatings(ratings);
		return hotel;
	}

	@Override
	public List<Hotel> getAllHotel() {	
		List<Hotel> hotels= hotelRepository.findAll();
		List<Hotel> hotels2= hotels.stream().map(hotel->{
			// using restTemplate
//			Rating[] ratings=restTemplate.getForObject("http://RATINGMICROSERVICE-1/ratings/hotel/"+hotel.getHotelId(),Rating [].class);
//			hotel.setRatings(Arrays.asList(ratings));
			// using feign client
			List<Rating> ratings=ratingClientService.getAllRatingsByHotelId(hotel.getHotelId());
			hotel.setRatings(ratings);
			return hotel;
		}).collect(Collectors.toList());
		return hotels2;
	}

	@Override
	public void deleteHotel(String hotelId) {
		Hotel hotel=getHotelById(hotelId);
		hotelRepository.delete(hotel);
	}

}
