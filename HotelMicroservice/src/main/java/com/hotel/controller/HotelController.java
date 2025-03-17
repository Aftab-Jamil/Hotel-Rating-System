package com.hotel.controller;

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

import com.hotel.entities.Hotel;
import com.hotel.payload.ApiResponse;
import com.hotel.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> hotels=hotelService.getAllHotel();
		return new ResponseEntity<List<Hotel>>(hotels,HttpStatus.OK);
	}
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
		Hotel hotel= hotelService.getHotelById(hotelId);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
		hotel.setHotelId(UUID.randomUUID().toString());
		Hotel hotel2=hotelService.saveHotel(hotel);
		return new ResponseEntity<Hotel>(hotel2,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel,@PathVariable String hotelId){
		Hotel savedHotelDetails=hotelService.getHotelById(hotelId);
		savedHotelDetails.setAbout(hotel.getAbout());
		savedHotelDetails.setLocation(hotel.getLocation());
		savedHotelDetails.setName(hotel.getName());
	    Hotel updatedHotel=hotelService.saveHotel(savedHotelDetails);
	    return new ResponseEntity<Hotel>(updatedHotel,HttpStatus.OK);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelId){
		hotelService.deleteHotel(hotelId);
		ApiResponse response=ApiResponse.builder().message("Deleted succesfully").status(HttpStatus.OK).success(true).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	
	

}
