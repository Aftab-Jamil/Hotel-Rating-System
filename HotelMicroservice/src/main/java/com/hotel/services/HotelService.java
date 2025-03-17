package com.hotel.services;

import java.util.List;

import com.hotel.entities.Hotel;

public interface HotelService {
	
	public Hotel saveHotel(Hotel hotel);
	public Hotel getHotelById(String hotelId);
	public List<Hotel> getAllHotel();
	public void deleteHotel(String hotelId);

}
