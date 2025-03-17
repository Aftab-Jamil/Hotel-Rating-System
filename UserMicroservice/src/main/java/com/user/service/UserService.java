package com.user.service;

import java.util.List;
import com.user.entities.User;

public interface UserService {
	public List<User> getAllUsers();
	public User getUser(String userId);
	public User saveUser(User user);
	public void deleteUser(String userId);
}
