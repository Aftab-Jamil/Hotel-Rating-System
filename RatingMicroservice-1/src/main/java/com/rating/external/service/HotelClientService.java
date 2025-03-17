package com.rating.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rating.entities.Hotel;

@FeignClient("HOTELMICROSERVICE")
public interface HotelClientService {
	@GetMapping("/hotel/{hotelId}")
	Hotel getHotelByHotelId(@PathVariable String hotelId);

}
