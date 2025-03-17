package com.hotel.entities;


import lombok.Data;

@Data
public class Rating {
	
	private String ratingId;
	private String userId;
	private int rating;
	private String feedback;

}
