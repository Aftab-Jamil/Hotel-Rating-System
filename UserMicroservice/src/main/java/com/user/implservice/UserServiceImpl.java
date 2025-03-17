package com.user.implservice;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exceptions.UserNotFound;
import com.user.external.service.RatingClientService;
import com.user.repository.UserRepository;
import com.user.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RatingClientService ratingClientService;
	@Override
	public List<User> getAllUsers() {
		List<User> users= userRepository.findAll();
		List<User> users2=users.stream().map(user->{
			// using RestTemplate
//			Rating[] ratings=restTemplate.getForObject("http://RATINGMICROSERVICE-1/ratings/user/"+user.getUserId() ,Rating [].class);
//			user.setRatings(Arrays.asList(ratings));
			// using feign client
			List<Rating> ratings=ratingClientService.getAllRatingsByUserId(user.getUserId());
			user.setRatings(ratings);
			return user;
		}).collect(Collectors.toList());
		return users2;
	}

	@Override
	public User getUser(String userId) {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFound("User not found with user id "+ userId));
		//using RestTemplate
//		Rating[] ratings=restTemplate.getForObject("http://RATINGMICROSERVICE-1/ratings/user/"+userId,Rating [].class);
//		user.setRatings(Arrays.asList(ratings));
		// using feign client
		List<Rating> ratings= ratingClientService.getAllRatingsByUserId(userId);
		user.setRatings(ratings);
		return user;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);	
	}

	@Override
	public void deleteUser(String userId) {
		User user=getUser(userId);
		userRepository.delete(user);
		
	}

}
