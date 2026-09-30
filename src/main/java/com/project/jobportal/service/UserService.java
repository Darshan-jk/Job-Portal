package com.project.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.jobportal.entity.User;
import com.project.jobportal.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(Long id){
		return userRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("User not found"));
	}
	
	public User createUser(User user){
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		
		return userRepository.save(user);
	}
	
	public User updateUser(Long id, User updatedUser) {
		User existingUser = getUserById(id);
		
		existingUser.setName(updatedUser.getName());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setPhone(updatedUser.getPhone());
		existingUser.setRole(updatedUser.getRole());
		
		return userRepository.save(existingUser);
	}
	
	public void deleteUser(Long id) {
		User user = getUserById(id);
		
		userRepository.delete(user);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
	}
	
}
